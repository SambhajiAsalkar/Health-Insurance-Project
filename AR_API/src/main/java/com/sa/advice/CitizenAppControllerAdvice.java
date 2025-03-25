package com.sa.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sa.exception.InvalidSSNException;

@RestControllerAdvice
public class CitizenAppControllerAdvice {

	@ExceptionHandler(InvalidSSNException.class)
	public ResponseEntity<String> handleInvalidSSNException(InvalidSSNException exp)
	{
		return new ResponseEntity<String>(exp.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception exp)
	{
		return new ResponseEntity<String>(exp.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
