package com.cc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cc.util.ErrorDetails;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
/**
 * Exception Controller Class to handle exception globally
 * @author Nitin Raut
 *
 */
@ControllerAdvice
public class CreditCardExceptionController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(Exception ex){
	
		return new ResponseEntity<>(new ErrorDetails(500,ex.getMessage(),ex.getClass()),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(CreditCardNotFound.class)
	public ResponseEntity<ErrorDetails> CreditCardNotFoundException(CreditCardNotFound cnf){
		
		return new ResponseEntity<>(new ErrorDetails(500,"Credit Card Not Found",cnf.getClass()),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> ValidationExceptions(MethodArgumentNotValidException ex){
		return new ResponseEntity<>(new ErrorDetails(500,ex.getBindingResult().getFieldError().getDefaultMessage(),ex.getClass()),HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorDetails> invalidFormatException(HttpMessageNotReadableException ex){
		return new ResponseEntity<>(new ErrorDetails(500,ex.getMessage(),ex.getClass()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<ErrorDetails> numberFormatException(InvalidFormatException ex){
		return new ResponseEntity<>(new ErrorDetails(500,ex.getOriginalMessage(),ex.getClass()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
