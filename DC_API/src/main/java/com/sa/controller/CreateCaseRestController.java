package com.sa.controller;

import java.util.Map; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.binding.CreateCaseResponse;
import com.sa.service.DcServiceImpl;

@RestController
@RequestMapping("/DataCollection")
public class CreateCaseRestController
{
    @Autowired
	DcServiceImpl service;
	@GetMapping("/case/{appId}")
	public ResponseEntity<CreateCaseResponse> createCase(@PathVariable Integer appId)
	{
		     Long caseNumber = service.loadCaseNumber(appId);
		     Map<Integer,String> map = service.getPlanName();
		     CreateCaseResponse response=new CreateCaseResponse();
		     response.setCaseNumber(caseNumber); 
		      response.setPlaneName(map);
		      
		      return new ResponseEntity<>(response, HttpStatus.OK);
		    
	}
}
