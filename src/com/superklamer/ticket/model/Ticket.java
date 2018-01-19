package com.superklamer.ticket.model;

public class Ticket {

	private int ticketNumber;
	private String ticketName; 
	private String ticketComment;

	public Ticket () {
		this(0000000001, null, null);
	}
	
	public Ticket(int ticketNumber, String ticketName, String ticketComment) {
		this.ticketNumber = ticketNumber;
		this.ticketName = ticketName;
		this.ticketComment = ticketComment;
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
