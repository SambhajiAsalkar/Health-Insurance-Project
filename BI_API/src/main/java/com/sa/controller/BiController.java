package com.sa.controller;

import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.service.IBenifitIssuenceImpl;

@RestController
@RequestMapping("/bi-api")
public class BiController {

	@Autowired
	private IBenifitIssuenceImpl service;
	
	@GetMapping("/benifit-issue")
	public ResponseEntity<String> writeCsv() throws Exception
	{
		JobExecution amt = service.sendBenificiaryAmt();
		return new ResponseEntity<String>(amt.getExitStatus().getExitDescription(),HttpStatus.OK);
	}
}
