package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.binding.CitizenApp;
import com.sa.exception.InvalidSSNException;
import com.sa.service.ArServiceImpl;

@RestController
@RequestMapping("/app")
public class ArRestController {

	@Autowired
	private ArServiceImpl service;
	
	@PostMapping("/register")
	public ResponseEntity<String> createCitizenApp(@RequestBody CitizenApp app) throws InvalidSSNException 
	{
		
		    Integer id = service.createApplication(app);
		    if(id==0) 
		    {
		    	return new ResponseEntity<>(" Customer Not Belong To New Jersey State ", HttpStatus.CREATED);
		    }
			return new ResponseEntity<>("Application is created with id "+id , HttpStatus.CREATED);
		}
	
}
