package me.mrCookieSlime.CSCoreLibPlugin.database;

import java.sql.Connection;

@Deprecated
public interface Database {
	
	Connection getConnection();
	String getIP();
	boolean isConnected();

}
