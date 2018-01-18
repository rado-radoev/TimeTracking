package com.superklamer.serialize;

import java.util.Date;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TimeWorked implements Serializable {
	

	private static final long serialVersionUID = 1928694256829314940L;
	
	private Date date;
	private double hoursWorked;
	
	private static final DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	
	public TimeWorked(Date date, double hoursWorked) {
		this.date = date;
		this.hoursWorked = hoursWorked;
	}


	public Date getDate() {
		return date;
	}


	public double getHoursWorked() {
		return hoursWorked;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public void setHoursWorked(float hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	
	
}
