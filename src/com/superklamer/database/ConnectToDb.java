package com.superklamer.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class ConnectToDb {

	public static void main(String[] args) {
		
		try {
			// et Connection to db
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tickets?autoReconnect=true&useSSL=false",
					"root", "rado");
			
			// create statement
			Statement myStatement = myConn.createStatement();
			
			// create query
			ResultSet myRes = myStatement.executeQuery("SELECT * FROM Tickets.Info");
			
			
			// process resultSet
			while (myRes.next()) {
				System.out.println(myRes.getString("TicketName") + " " + myRes.getString("TicketNumber"));
			}
			
			
			ResultSet myTimeRes = myStatement.executeQuery("SELECT * FROM Tickets.Time");
			while (myTimeRes.next()) {
				System.out.println(myTimeRes.getString("Date") + " " + myTimeRes.getString("HoursWorked") + " " + myTimeRes.getString("TicketNumber"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
