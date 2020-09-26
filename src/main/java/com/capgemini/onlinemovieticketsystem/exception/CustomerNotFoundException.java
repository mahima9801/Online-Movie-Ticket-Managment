package com.capgemini.onlinemovieticketsystem.exception;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String msg)
	{
		super(msg);
	}
}
