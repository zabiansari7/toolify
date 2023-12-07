package de.srh.toolify.exceptions;

public class PurchaseItemsException extends RuntimeException{
	
	private static final long serialVersionUID = 8136240178325175400L;

	public PurchaseItemsException(String message, Throwable err) {
		super(message, err);
	}

}
