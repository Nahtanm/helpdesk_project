package com.dev_santos.helpdesk.services.exceptions;

public class ExistingObjectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExistingObjectException(String message) {
		super(message);
	}
	
}
