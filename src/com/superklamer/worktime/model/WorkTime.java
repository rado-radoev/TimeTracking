package com.superklamer.worktime.model;

import java.util.Date;


public class WorkTime {

	private int ticketNumber;
	private Date dateWorked;
	private double hoursWorked;
	
	
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
