package com.superklamer.serialize;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DeserializerTest {
	
	public static void main(String[] args) {
		
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream("database.properties"));
		} catch (FileNotFoundException fnf) {
			System.err.println(fnf.getMessage());
		} catch (SecurityException se) {
			System.err.println(se.getMessage());
		} catch (IOException io) {
			System.err.println(io.getMessage());
		}
		
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String dburl = properties.getProperty("dburl");
		
		try (Connection conn = DriverManager.getConnection(dburl, user, password)) {		
			System.out.println("DB connection successful to: " + dburl.substring(33));
		
			Statement statement = null;
			ResultSet resultSet = null;
			
			InputStream input = null;
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			
			statement = conn.createStatement();
			String sql = "SELECT Time FROM Tickets.Time WHERE TicketNumber='23455'";
			TimeWorked tw = new TimeWorked();
			
			if (resultSet.next()) {
				input = resultSet.getBinaryStream("Time");
				System.out.println("Reading Time from database ...");
				System.out.println(sql);
				
				byte[] data = new byte[1024];
				while (input.read(data) > 0) {
					buffer.write(data);
				}
			}
			
			TimeWorked timeWorked = (TimeWorked)Serializer.deserialize(buffer.toByteArray());
			
			System.out.println("Date: " + timeWorked.getDate() + " " + "Hours: " +timeWorked.getHoursWorked());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
