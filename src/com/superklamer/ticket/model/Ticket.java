package com.superklamer.ticket.model;

import java.util.Date;

import com.superklamer.worktime.model.WorkTime;

public class Ticket {

	private int ticketNumber;
	private String ticketName; 
	private String ticketComment;
	private WorkTime workTime;

	
	public static class Builder {
		// required parameters
		private final int ticketNumber;
		private final String ticketName;
		
		// opitonal parameters - initalized to default values
		private String ticketComment = null;
		private WorkTime workTime = new WorkTime();
		
		public Builder(int ticketNumber, String ticketName) {
			this.ticketNumber = ticketNumber;
			this.ticketName = ticketName;
		}
		
		public Builder ticketComment(String ticketComment) {
			this.ticketComment = ticketComment;
			return this;
		}
		
		public Builder workTime(WorkTime workTime) {
			this.workTime = workTime;
			return this;
		}
		
		public Ticket build() {
			return new Ticket();
		}
	}
	
	private Ticket(Builder builder) {
		ticketNumber = builder.ticketNumber;
		ticketName = builder.ticketName;
		ticketComment = builder.ticketComment;
		workTime = builder.workTime;
	}
	
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
