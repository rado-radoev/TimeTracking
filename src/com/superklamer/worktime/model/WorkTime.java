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
}
