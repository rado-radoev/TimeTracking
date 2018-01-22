package com.superklamer.database;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ConnectToDBTest {
	
	public static void main(String[] args) {
		
		
	try (ConnectToDB connect = ConnectToDB.getInstance()) {
			connect.setConnection("jdbc:mysql://localhost:3306/Tickets?autoReconnect=true&useSSL=false", "root", "rado");
			Statement statement = connect.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Tickets.TicketData");
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnNumber = resultSetMetaData.getColumnCount();
			System.out.println(columnNumber);
			
			for (int i = 1; i <= columnNumber; i++) {
				System.out.println(resultSetMetaData.getColumnName(i));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}


