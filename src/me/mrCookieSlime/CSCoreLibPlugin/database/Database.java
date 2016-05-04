package me.mrCookieSlime.CSCoreLibPlugin.database;

import java.sql.Connection;

public interface Database {
	
	Connection getConnection();
	String getIP();
	boolean isConnected();

}
