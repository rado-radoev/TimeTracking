package com.superklamer.database;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ConnectToDBTest {
	
	public static void main(String[] args) {
		try (ConnectToDB connect = new ConnectToDB("root", "rado", "jdbc:mysql://localhost:3306/Tickets?autoReconnect=true&useSSL=false")) {
			try {
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

}
