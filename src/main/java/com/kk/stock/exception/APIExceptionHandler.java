package com.kk.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kk.stock.model.ErrorMessage;

import io.micrometer.core.ipc.http.HttpSender.Response;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler({StockNotFoundException.class})
	ResponseEntity<ErrorMessage> handleStockNotFoundException(StockNotFoundException exp){
		ErrorMessage errMessage = new ErrorMessage();
		errMessage.setCode("STOCK_1001");
		errMessage.setMessage(exp.getMessage());
		return new ResponseEntity<ErrorMessage>(errMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({DuplicateStockException.class})
	ResponseEntity<ErrorMessage> handleDuplicateStockException(DuplicateStockException exp){
		ErrorMessage errMessage = new ErrorMessage();
		errMessage.setCode("STOCK_1002");
		errMessage.setMessage(exp.getMessage());
		return new ResponseEntity<ErrorMessage>(errMessage, HttpStatus.BAD_REQUEST);
	}

}
