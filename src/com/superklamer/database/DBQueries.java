package com.superklamer.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import com.superklamer.ticket.model.Ticket;
import com.superklamer.worktime.model.WorkTime;

public class DBQueries {
	
	private PreparedStatement queryTicketInfoWithTicketNumber;
	private PreparedStatement queryTicketInfoWithTicketName;
	private PreparedStatement queryFullTicketInfoWithTicketNumber;
	private PreparedStatement queryAllTicketsInfo;
	private PreparedStatement queryTicketWorkTime;
	private PreparedStatement queryAllTicketsWorkTime;
	private PreparedStatement createNewTicket;
	private PreparedStatement createNewWorkTime;
	ConnectToDB dbConnection;
	
	
	// TO DO: THIS MAY BE BETTER AS A STATIC CLASS
	public DBQueries() {
		try {
			dbConnection = ConnectToDB.getInstance();
			
			// *** queries *** 
			String allTicketsInfo = "SELECT * FROM Tickets.TicketData";
			String ticketInfoWithTicketName = "SELECT * FROM Tickets.TicketData WHERE TicketName = ?";
			String ticketInfoWithTicketNumber = "SELECT * FROM Tickets.TicketData WHERE TicketNumber = ?";
			String fullTicketInfoWithTicketNumber = "SELECT TicketData.TicketNumber, TicketData.TicketName, TicketData.TicketComment, TicketHours.Date, TicketHours.Hours " +
					"FROM Tickets.TicketData " + 
					"INNER JOIN "+ 
					"TicketHours " + 
					"ON " + 
					"TicketData.TicketNumber = TicketHours.TicketNumber " +
					"WHERE " + 
					"TicketData.TicketNumber = ?";
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
			
			// crate a querty that will return full ticket info when ticket number is provided
			queryFullTicketInfoWithTicketNumber = 
					dbConnection.getConnection().prepareStatement(fullTicketInfoWithTicketNumber);
			
			
			// *** inserts ***
			String insertNewTicket = "INSERT INTO Tickets.TicketData" + 
									"(TicketNumber, TicketName, TicketComment)" + 
									"VALUES (?, ?, ?)";
			
			String insertNewWorkTime = "INSERT INTO Tickets.TicketHours" + 
									  "(TicketNumber, Date, Hours)" + 
									  "VALUES (?, ?, ?)";
			
			// create a new ticket 
			createNewTicket = 
					dbConnection.getConnection().prepareStatement(insertNewTicket);
			
			// create new workTime
			createNewWorkTime = 
					dbConnection.getConnection().prepareStatement(insertNewWorkTime);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// query that will return full ticket info
	public List< Ticket > getFullTicketInfo(int ticketNumber) {
		List < Ticket > result = null;
		ResultSet resultSet = null;
		
		try {
			queryFullTicketInfoWithTicketNumber.setInt(1, ticketNumber);
			resultSet = queryFullTicketInfoWithTicketNumber.executeQuery();
			result = new ArrayList<Ticket>();
			
			while (resultSet.next()) {
				// TO DO: DECIDE IF I NEED TO NULL EACH OBJ ON EVERY ITERATION
				WorkTime workTime = new WorkTime.Builder(resultSet.getInt("TicketNumber"))
						.dateWorked(resultSet.getDate("Date"))
						.hoursWorked(resultSet.getDouble("Hours"))
						.build();
				
				Ticket tick = new Ticket.Builder(resultSet.getInt("TicketNumber"), resultSet.getString("TicketName"))
						.ticketComment(resultSet.getString("TicketComment"))
						.workTime(workTime)
						.build();
				
				result.add(tick);
			
			}
			
			

		} catch (SQLException sqe) {
			sqe.printStackTrace();
		} 
		
		return result;
	}
	
	// query specific ticket work time
	public List<WorkTime> getTicketWorkTime(int ticketNumber) {
		List< WorkTime > result = null;
		ResultSet resultSet = null;
		
		try {
			queryTicketWorkTime.setInt(1, ticketNumber);
			resultSet = queryTicketWorkTime.executeQuery();
			result = new ArrayList< WorkTime >();
			
			while (resultSet.next()) {
				result.add(new WorkTime(
						resultSet.getInt("TicketNumber"),
						resultSet.getDate("Date"), 
						resultSet.getDouble("Hours")));
			}
			
			
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		
		return result;
	}
	
	// query all ticket and return time worked
	public List< WorkTime > getAllTicketsTimeWorked() {
		List< WorkTime > result = null;
		ResultSet resultSet = null;
		
		try {
			resultSet = queryAllTicketsWorkTime.executeQuery();
			result = new ArrayList<WorkTime>();
			
			while (resultSet.next()) {
				result.add(new WorkTime(
						resultSet.getInt("TicketNumber"), 
						resultSet.getDate("Date"), 
						resultSet.getDouble("Hours")));
			}
			
			
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		
		return result;
	}
	
	// query info for specific ticket when ticket name provided
	public Ticket getTicketInfoWithTicketName(String ticketName) {
		ResultSet resultSet = null;
		
		try {
			queryTicketInfoWithTicketName.setString(1, ticketName);
			resultSet = queryTicketInfoWithTicketName.executeQuery();
			
			if (resultSet.next()) {
				return new Ticket(
						resultSet.getInt("TicketNumber"),
						resultSet.getString("TicketName"),
						resultSet.getString("TicketComment"));
			}
			
			
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		
		return new Ticket();
	}
	
	// query info for specific ticket when ticket number provided
	public Ticket getTicketInfoWithTicketNumber(int ticketNumber) {
		ResultSet resultSet = null;
		
		try {
			queryTicketInfoWithTicketNumber.setInt(1, ticketNumber);
			resultSet = queryTicketInfoWithTicketNumber.executeQuery();
			
			if (resultSet.next()) {
				return new Ticket(resultSet.getInt("TicketNumber"),
									resultSet.getString("TicketName"),
									resultSet.getString("TicketComment"));
			}
			
			
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		
		return new Ticket();	
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
			
			dbConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// create new work time in the db
	public int addTime(int ticketNumber, Date date, Double hours) {
		int result = 0;
		
		// set params and execute insert
		try {
			Calendar c = Calendar.getInstance();
			createNewWorkTime.setInt(1, ticketNumber);
			createNewWorkTime.setDate(2, convertUtilToSql(date));
			createNewWorkTime.setDouble(3, hours);
			
			// insert the new row and return the # of rows updated
			result = createNewWorkTime.executeUpdate();
			
			dbConnection.close();
			
		} catch (SQLException sqe) {
			sqe.printStackTrace();
		}
		
		return result;
	}
	
	private java.sql.Date convertUtilToSql (java.util.Date uDate) {
		java.sql.Date sDate = new java.sql.Date(uDate.getTime());
		return sDate;
	}
}
