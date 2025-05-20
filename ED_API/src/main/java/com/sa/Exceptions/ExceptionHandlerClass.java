package com.sa.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {

	@ExceptionHandler(value = EdExceptions.class)
	public ResponseEntity<ExceptionInfo> EdExceptionHandling(EdExceptions ee)
	{
		String message = ee.getMessage();
		ExceptionInfo ei=new ExceptionInfo();
		
		ei.setStatusCode(401);
		ei.setMsg(message);
		
		return new ResponseEntity<ExceptionInfo>(ei,HttpStatus.OK);
	}
}
