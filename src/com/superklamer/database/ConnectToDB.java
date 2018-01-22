package com.superklamer.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectToDB implements AutoCloseable {

	private Connection connection;
	private String userName;
	private String password;
	private String dbURL;
	
	public ConnectToDB() {
		this(null, null, null);
	}
	
	public ConnectToDB(String userName, String password, String dbURL) {
		this.userName = userName;
		this.password = password;
		this.dbURL = dbURL;
		
		setConnection();
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection() {
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
