package com.kk.stock.exception;

public class StockNotFoundException extends RuntimeException {
	String message;
	
	public StockNotFoundException(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	

}
