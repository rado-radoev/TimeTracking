package com.superklamer.worktime.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkTime {

	private int ticketNumber;
	private Date dateWorked;
	private double hoursWorked;
	
	private static DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
	
	public static class Builder {
		// required parameters
		private final int ticketNumber;
		
		// optional params
		private Date dateWorked;
		private double hoursWorked;
		
		public Builder(int ticketNumber) {
			this.ticketNumber = ticketNumber;
		}
		
		public Builder dateWorked(Date dateWorked) {
			this.dateWorked = dateWorked;
			return this;
		}
		
		public Builder hoursWorked(double hoursWorked) {
			this.hoursWorked = hoursWorked;
			return this;
		}
		
		public WorkTime build() {
			return new WorkTime();
		}
	}
	
	private WorkTime(Builder builder) {
		this.ticketNumber = builder.ticketNumber;
		this.dateWorked = builder.dateWorked;
		this.hoursWorked = builder.hoursWorked;
	}
	
	
	public WorkTime () {
		this(00000000001, null, 0.0);
	}
	
	public WorkTime(int ticketNumber, Date dateWorked, double hoursWorked) {
		this.ticketNumber = ticketNumber;
		this.dateWorked = dateWorked;
		this.hoursWorked = hoursWorked;
	}

	public int getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public Date getDateWorked() {
		return dateWorked;
	}
	
	public String getDateWorkedAsString() {
		return dateFormat.format(getDateWorked());
	}

	public void setDateWorked(Date dateWorked) {
		this.dateWorked = dateWorked;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
}
