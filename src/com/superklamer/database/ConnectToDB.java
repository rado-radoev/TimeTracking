package com.superklamer.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectToDB implements AutoCloseable {

	private static ConnectToDB instance = null;
	private Connection connection;
	private String userName;
	private String password;
	private String dbURL;
	
	private ConnectToDB() {
		// Empty constructor prevents instantiation 
	}
	
	public static ConnectToDB getInstance() {
		if (instance == null) {
			instance = new ConnectToDB();
		}
		
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(String dbURL, String userName, String password) {
		this.dbURL = dbURL;
		this.userName = userName;
		this.password = password;
		
		try {
			this.connection = DriverManager.getConnection(getDbURL(), getUserName(), getPassword());
		} catch (SQLException e) {
			System.out.println("Connection to: \n" + getDbURL() + "\ncould not be establieshed!");
			e.printStackTrace();
		}
		
	}

	private String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	private String getPassword() {
		return password;
	}

	public final void setPassword(String password) {
		this.password = password;
	}

	public String getDbURL() {
		return dbURL;
	}

	public final void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	@Override
	public final void close(){
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
