package de.srh.toolify.exceptions;

public class PurchaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PurchaseException(String message, Throwable err) {
		super(message, err);
	}

}
