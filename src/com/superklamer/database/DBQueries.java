package com.superklamer.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import com.superklamer.ticket.model.Ticket;

public class DBQueries {
	
	private Connection connection;
	private PreparedStatement queryTicketInfoWithTicketNumber;
	private PreparedStatement queryTicketInfoWithTicketName;
	private PreparedStatement queryAllTicketsInfo;
	private PreparedStatement queryTicketWorkTime;
	private PreparedStatement queryAllTicketsWorkTime;
	private PreparedStatement createNewTicket;
	ConnectToDB dbConnection;
	
	public DBQueries() {
		try {
			dbConnection = ConnectToDB.getInstance();
			
			// *** queries *** 
			String allTicketsInfo = "SELECT * FROM Tickets.TicketData";
			String ticketInfoWithTicketName = "SELECT * FROM Tickets.TicketData WHERE TicketName = ?";
			String ticketInfoWithTicketNumber = "SELECT * FROM Tickets.TicketData WHERE TicketNumber = ?";
			String allTicketsWorkTime = "SELECT * FROM Tickets.TicketHours";
			String ticketWorkTime = "SELECT * FROM Tickets.TicketHours WHERE TicketNumber = ?"; 
			
			
			// create a query that will return information for all tickets;
			queryAllTicketsInfo = 
					dbConnection.getConnection().prepareStatement(allTicketsInfo);
			
			// create a query that will return information for a specific ticket when ticket number is provided
			queryTicketInfoWithTicketNumber =
					dbConnection.getConnection().prepareStatement(ticketInfoWithTicketNumber);
			
			// create a query that will return information for a specific ticket when ticket name is provided
			queryTicketInfoWithTicketName = 
					dbConnection.getConnection().prepareStatement(ticketInfoWithTicketName);
			
			
			// create a query that will return time worked for all tickets
			queryAllTicketsWorkTime = 
					dbConnection.getConnection().prepareStatement(allTicketsWorkTime);
			
			// create a query that will return time worked for a specific ticket when providing ticket number
			queryTicketWorkTime = 
					dbConnection.getConnection().prepareStatement(ticketWorkTime);
			
			// *** inserts ***
			String insertNewTicket = "INSERT INTO Tickets.TicketData" + 
									"(TicketNumber, TicketName, TicketComment)" + 
									"VALUES (?, ?, ?)";
			
			// create a new ticket 
			createNewTicket = 
					dbConnection.getConnection().prepareStatement(insertNewTicket);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// query all tickets in the db
	public List< Ticket > getAllTicketsInfo() {
		List< Ticket > results = null;
		ResultSet resultSet = null;
		
		
		try {
			resultSet = queryAllTicketsInfo.executeQuery();
			results = new ArrayList<Ticket>();
			
			while (resultSet.next()) {
				results.add(new Ticket(
						resultSet.getInt("TicketNumber"),
						resultSet.getString("TicketName"),
						resultSet.getString("TicketComment")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dbConnection.close();
		}
		
		return results;
	}

	// create new ticket in the db
	public int addTicket(int ticketNumber, String ticketName, String ticketComment) {
		int result = 0;
		
		// set parameters, then execute insert
		try {
			createNewTicket.setInt(1, ticketNumber);
			createNewTicket.setString(2, ticketName);
			createNewTicket.setString(3, ticketComment);
			
			// insert the new entry & return # of rows updated
			result = createNewTicket.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
