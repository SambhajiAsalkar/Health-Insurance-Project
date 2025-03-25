package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.binding.ChildRequest;
import com.sa.binding.Summary;
import com.sa.service.DcServiceImpl;

@RestController
@RequestMapping("/DataCollection")
public class ChildRestController {

	@Autowired
	private DcServiceImpl service;
	
	@PostMapping("/saveKids")
	public ResponseEntity<Summary> saveKids(@RequestBody ChildRequest kids)
	{
		Long caseNumb = service.saveKidsDetails(kids);
		   
		    
		     Summary summary = service.getSummary(caseNumb);
		return new ResponseEntity<>(summary,HttpStatus.CREATED);
	}
}
