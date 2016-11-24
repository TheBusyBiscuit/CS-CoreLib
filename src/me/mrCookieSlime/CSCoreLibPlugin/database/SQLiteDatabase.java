package me.mrCookieSlime.CSCoreLibPlugin.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;

import org.bukkit.plugin.Plugin;

public class SQLiteDatabase implements Database {
	
	private String prefix, name;
	private Plugin plugin;
	private Connection connection;

	public SQLiteDatabase(Plugin plugin, String name) {
		this.plugin = plugin;
		this.name = name;
		
		Config cfg = new Config("plugins/" + plugin.getName() + "/database.cfg");
		cfg.setDefaultValue("table_prefix", plugin.getName().toLowerCase().replace(" ", "") + "_");
		cfg.save();
		
		this.prefix = cfg.getString("table_prefix");
		
		getConnection();
	}
	
	@Override
	public Connection getConnection() {
		if (isConnected()) return this.connection;
		
		try {
			System.out.println("[CS-CoreLib - Database] Loading SQL Driver...");
			Class.forName("org.sqlite.JDBC");
		} catch(Exception x) {
			x.printStackTrace();
			System.err.println("ERROR: Failed to load SQL Driver: org.sqlite.JDBC");
			return null;
		}
		
		System.out.println("[CS-CoreLib - Database] Attempting to connect to local Database \"" + this.name + "\"");
		System.out.println("Request sent by Plugin: " + this.plugin.getName());
		System.out.println("IP: " + getIP());
		
		try {
	        Connection c = DriverManager.getConnection(getIP());
			System.out.println("> Connection Result: SUCCESSFUL");
			this.connection = c;
			return c;
		} catch(Exception x) {
			System.err.println("> Connection Result: FAILED");
			x.printStackTrace();
			System.err.println("ERROR: Could not connect to local Database \"" + this.name + "\"");
			return null;
		}
	}

	@Override
	public String getIP() {
		return "jdbc:sqlite:" + new File("plugins/" + plugin.getName() + "/" + this.name + ".db").getAbsolutePath();
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
	
	public String formatTableName(String name) {
		return this.prefix + name;
	}

}
