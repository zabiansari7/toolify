package de.srh.toolify.exceptions;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserException(String message, Throwable err) {
		super(message, err);
	}
}
