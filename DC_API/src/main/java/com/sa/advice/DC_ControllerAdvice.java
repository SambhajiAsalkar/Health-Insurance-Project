package com.sa.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DC_ControllerAdvice {

	@ExceptionHandler(Exception.class)
	private ResponseEntity<String> handleAllException(Exception exp) {
          return new ResponseEntity<String> (exp.getMessage(),HttpStatus.BAD_REQUEST); 
	}
}
