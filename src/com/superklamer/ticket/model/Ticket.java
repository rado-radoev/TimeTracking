package com.superklamer.ticket.model;

import java.util.Date;

import com.superklamer.worktime.model.WorkTime;

public class Ticket {

	private int ticketNumber;
	private String ticketName; 
	private String ticketComment;
	private WorkTime workTime;

	public Ticket () {
		this(0000000001, null, null);
	}

	public Ticket(int ticketNumber, String ticketName, String ticketComment) {
		this(ticketNumber, ticketName, ticketComment, null);
	}
	
	public Ticket(int ticketNumber, String ticketName, String ticketComment, WorkTime workTime) {
		this.ticketNumber = ticketNumber;
		this.ticketName = ticketName;
		this.ticketComment = ticketComment;
		this.workTime = workTime;
	}

	public WorkTime getWorkTime() {
		return workTime;
	}

	public void setWorkTime(WorkTime workTime) {
		this.workTime = workTime;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public String getTicketName() {
		return ticketName;
	}

	public String getTicketComment() {
		return ticketComment;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public void setTicketComment(String ticketComment) {
		this.ticketComment = ticketComment;
	}
	
	
}
