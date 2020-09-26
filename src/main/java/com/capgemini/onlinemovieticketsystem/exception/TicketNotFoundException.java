package com.capgemini.onlinemovieticketsystem.exception;

public class TicketNotFoundException extends RuntimeException {

	public  TicketNotFoundException(String msg)
	{
		super(msg);
	}
}
