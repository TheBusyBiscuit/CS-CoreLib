package me.mrCookieSlime.CSCoreLibPlugin.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;

import org.bukkit.plugin.Plugin;

public class MySQLDatabase implements Database {
	
	private Plugin plugin;
	
	private String host, user, password, database, prefix;
	private int port;
	
	private Connection connection;
	
	private Statement statement;
	
	public MySQLDatabase(Plugin plugin) {
		this.plugin = plugin;
		
		Config cfg = new Config("plugins/" + plugin.getName() + "/database.cfg");
		cfg.setDefaultValue("host", "localhost");
		cfg.setDefaultValue("port", 3306);
		cfg.setDefaultValue("username", "username");
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
		System.out.println("IP: " + getIP());
		System.out.println("Username: " + user.replaceAll("(?s).", "*"));
		System.out.println("Password: " + password.replaceAll("(?s).", "*"));
		
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
			return this.connection != null || this.connection.isValid(1);
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
		ResultSet set = statement.executeQuery(query);
		set.next();
		return set;
	}
	
	public ResultSet selectFromTable(String table) throws SQLException {
		return selectFromTable(table, new String[] {"*"});
	}
	
	public ResultSet selectFromTable(String table, String[] columns) throws SQLException {
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
	
	public ResultSet selectFromTable(String table, String[] columns, Map<String, String> requirements) throws SQLException {
		StringBuilder builder = new StringBuilder("SELECT ");
		int i = 0;
		for (String column: columns) {
			if (i == 0) builder.append(column);
			else builder.append(", " + column);
			i++;
		}
		builder.append(" FROM " + formatTableName(table) + " WHERE ");
		
		int j = 0;
		for (Map.Entry<String, String> entry: requirements.entrySet()) {
			if (j == 0) builder.append(entry.getKey() + "='" + entry.getValue() + "'");
			else builder.append(" AND " + entry.getKey() + "='" + entry.getValue() + "'");
			j++;
		}
		builder.append(";");
		return getResults(builder.toString());
	}
	
	public ResultSet selectFromTable(String table, Map<String, String> requirements) throws SQLException {
		return selectFromTable(table, new String[] {"*"}, requirements);
	}
	
	public void createTable(String table, String primary_key, String... parameters) {
		StringBuilder builder = new StringBuilder("CREATE TABLE IF NOT EXISTS " + formatTableName(table) + " (");
		int i = 0;
		for (String param: parameters) {
			if (i == 0) builder.append(param);
			else builder.append(",  " + param);
			i++;
		}
		builder.append(",  PRIMARY KEY ( " + primary_key + " ))");
		update(builder.toString());
	}
}
