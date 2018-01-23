package com.superklamer.database;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;

import com.superklamer.ticket.model.Ticket;
import com.superklamer.worktime.model.WorkTime;

public class ConnectToDBTest {
	
	public static void main(String[] args) {
		
		
	try (ConnectToDB connect = ConnectToDB.getInstance()) {
			connect.setConnection("jdbc:mysql://localhost:3306/Tickets?autoReconnect=true&useSSL=false", "root", "rado");
			Statement statement = connect.getConnection().createStatement();
//			ResultSet resultSet = statement.executeQuery("SELECT * FROM Tickets.TicketData");
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Tickets.TicketHours");
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnNumber = resultSetMetaData.getColumnCount();
			//System.out.println(columnNumber);
			
			for (int i = 1; i <= columnNumber; i++) {
				System.out.print(resultSetMetaData.getColumnName(i) + "\t");
			}
			System.out.println();
			
			DBQueries dbQuery1 = new DBQueries();
			//dbQuery1.addTicket(1234567890, "Test Ticket 1", "Comment for test ticket 1");
//			List<Ticket> tickets = dbQuery1.getAllTicketsInfo();
//			
//			for (Ticket ticket : tickets) {
//				System.out.print( ticket.getTicketNumber() + "\t" + ticket.getTicketName() + "\t" + ticket.getTicketComment());
//			}
			
//			int i = dbQuery1.addTime(1234567890, new Date(), 4.0);
			
			List<WorkTime> workTime = dbQuery1.getTicketWorkTime(1234567890);
			for (WorkTime wt : workTime) {
				System.out.print(wt.getTicketNumber() + "\t" + wt.getDateWorked() + "\t" + wt.getHoursWorked() );
			System.out.println();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}


