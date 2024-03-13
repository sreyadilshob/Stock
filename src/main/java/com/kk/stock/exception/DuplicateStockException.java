package com.kk.stock.exception;

public class DuplicateStockException extends RuntimeException {
	
	String message;
	
	public DuplicateStockException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	

}
