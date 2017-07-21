package me.mrCookieSlime.CSCoreLibPlugin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.plugin.Plugin;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;

public class MySQLDatabase implements Database {
	
	private Plugin plugin;
	
	private String host, user, password, database, prefix;
	private int port;
	
	private Connection connection;
	
	public MySQLDatabase(Plugin plugin) {
		this.plugin = plugin;
		
		Config cfg = new Config("plugins/" + plugin.getName() + "/database.cfg");
		cfg.setDefaultValue("host", "localhost");
		cfg.setDefaultValue("port", 3306);
		cfg.setDefaultValue("username", "root");
		cfg.setDefaultValue("password", "password");
		cfg.setDefaultValue("database", "database");
		cfg.setDefaultValue("table_prefix", plugin.getName().toLowerCase().replace(" ", "") + "_");
		cfg.save();
		
		this.host = cfg.getString("host");
		this.port = cfg.getInt("port");
		this.user = cfg.getString("username");
		this.password = cfg.getString("password");
		this.database = cfg.getString("database");
		this.prefix = cfg.getString("table_prefix");
		
		getConnection();
	}
	
	@Override
	public Connection getConnection() {
		if (isConnected()) return this.connection;
		try {
			System.out.println("[CS-CoreLib - Database] Loading SQL Driver...");
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception x) {
			System.err.println("ERROR: Failed to load SQL Driver: com.mysql.jdbc.Driver");
			x.printStackTrace();
			return null;
		}
		
		System.out.println("[CS-CoreLib - Database] Attempting to connect to Database \"" + this.database + "\"");
		System.out.println("Request sent by Plugin: " + this.plugin.getName());
		
		try {
			Connection connection = DriverManager.getConnection(getIP(), this.user, this.password);
			this.connection = connection;
			System.out.println("> Connection Result: SUCCESSFUL");
			return connection;
		} catch (Exception x) {
			System.err.println("> Connection Result: FAILED");
			System.err.println(" ");
			System.err.println("ERROR: Double-check the Host and Credentials you specified in the \"database.cfg\" under /plugins/" + plugin.getName() + "/database.cfg");
			x.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String getIP() {
		return "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database;
	}

	@Override
	public boolean isConnected() {
		try {
			return this.connection != null && this.connection.isValid(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void closeResources(ResultSet set, PreparedStatement statement) {
		try {
			if (set != null) set.close();
			if (statement != null) statement.close();
		} catch(Exception x) {
			x.printStackTrace();
		}
	}

	public void closeConnection() throws SQLException {
		if (!isConnected()) return;
		this.connection.close();
		this.connection = null;
	}
	
	public void update(String query) {
		PreparedStatement statement = null;
		try {
			statement = getConnection().prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(null, statement);
		}
	}
	
	public String formatTableName(String name) {
		return this.prefix + name;
	}
	
	public ResultSet getResults(String query) throws SQLException {
		Statement statement = connection.createStatement();
		
		return statement.executeQuery(query);
	}
	
	public ResultSet selectFromTable(String table) throws SQLException {
		return selectFromTable(table, "*");
	}
	
	public ResultSet selectFromTable(String table, TableValue... requirements) throws SQLException {
		return selectFromTable(table, new String[] {"*"}, requirements);
	}
	
	public ResultSet selectFromTable(String table, String... columns) throws SQLException {
		StringBuilder builder = new StringBuilder("SELECT ");
		int i = 0;
		for (String column: columns) {
			if (i == 0) builder.append(column);
			else builder.append(", " + column);
			i++;
		}
		builder.append(" FROM " + formatTableName(table));
		return getResults(builder.toString());
	}
	
	public ResultSet selectFromTable(String table, String[] columns, TableValue... requirements) throws SQLException {
		StringBuilder builder = new StringBuilder("SELECT ");
		
		boolean start = true;
		for (String column: columns) {
			if (start) builder.append(column);
			else builder.append(", " + column);
			
			start = false;
		}
		
		builder.append(" FROM " + formatTableName(table) + " WHERE ");
		
		boolean start2 = true;
		for (TableValue value: requirements) {
			if (start2) builder.append(value.key + "='" + value.value + "'");
			else builder.append(" AND " + value.key + "='" + value.value + "'");
			
			start2 = false;
		}
		
		builder.append(";");
		return getResults(builder.toString());
	}
	
	public void clearTable(String table, TableValue... requirements) {
		StringBuilder builder = new StringBuilder("DELETE FROM " + formatTableName(table) + " WHERE ");
		
		boolean start = true;
		for (TableValue value: requirements) {
			if (start) builder.append(value.key + "='" + value.value + "'");
			else builder.append(" AND " + value.key + "='" + value.value + "'");
			
			start = false;
		}
		
		builder.append(";");
		
		update(builder.toString());
	}
	
	public void clearTable(String table) {
		update("DELETE FROM " + formatTableName(table) + ";");
	}
	
	public void deleteTable(String table) {
		update("DROP TABLE " + formatTableName(table) + ";");
	}
	
	public void insertTable(String table, boolean replace, TableValue... values) {
		StringBuilder prefix = new StringBuilder("INSERT INTO " + formatTableName(table) + " (");
		StringBuilder middle = new StringBuilder(") VALUES (");
		StringBuilder suffix = new StringBuilder(") ON DUPLICATE KEY UPDATE ");
		boolean start = true;
		
		for (TableValue value: values) {
			if (start) {
				prefix.append(value.key);
				middle.append("'" + value.value + "'");
				suffix.append(value.toString());
			}
			else {
				prefix.append(", " + value.key);
				middle.append(", '" + value.value + "'");
				suffix.append(", " + value.toString());
			}
			
			start = false;
		}
		
		if (replace) update(prefix.toString() + middle.toString() + suffix.toString() + ";");
		else update(prefix.toString() + middle.toString() + ");");
	}
	
	public void insertTable(String table, String... values) {
		StringBuilder builder = new StringBuilder("INSERT INTO " + formatTableName(table) + " VALUES (");
		boolean start = true;
		for (String value: values) {
			if (start) builder.append("'" + value + "'");
			else builder.append(", '" + value + "'");
			start = false;
		}
		builder.append(");");
		update(builder.toString());
	}
	
	public void createTable(String table, String primary_key, SQLColumn... parameters) {
		StringBuilder builder = new StringBuilder("CREATE TABLE IF NOT EXISTS " + formatTableName(table) + " (");
		int i = 0;
		for (SQLColumn param: parameters) {
			if (i == 0) builder.append(param.toString());
			else builder.append(", " + param.toString());
			i++;
		}
		builder.append(",  PRIMARY KEY ( " + primary_key + " ));");
		update(builder.toString());
	}
	
	public void addTableColumn(String table, SQLColumn column) {
		update("ALTER TABLE " + formatTableName(table) + " ADD " + column.toString() + ";");
	}
	
	public void removeTableColumn(String table, String column) {
		update("ALTER TABLE " + formatTableName(table) + " DROP COLUMN " + column + ";");
	}
}