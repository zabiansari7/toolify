package de.srh.toolify.exceptions;

public class AddressException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public AddressException(String message, Throwable err) {
		super(message, err);
	}
}
