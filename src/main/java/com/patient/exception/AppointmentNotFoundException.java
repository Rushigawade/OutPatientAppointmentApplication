package com.patient.exception;

public class AppointmentNotFoundException extends RuntimeException {
	
	AppointmentNotFoundException(String message)
	{
		super(message);
	}

}
