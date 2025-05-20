package com.sa.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.binding.ChildRequest;
import com.sa.binding.CreateCaseResponse;
import com.sa.binding.EducationDetails;
import com.sa.binding.IncomeDetails;
import com.sa.binding.PlanSelection;
import com.sa.binding.Summary;
import com.sa.service.DcServiceImpl;

@RestController
@RequestMapping("/dc-api")
public class DcController {

	@Autowired
	private DcServiceImpl service;
	
	@PostMapping("/saveKids")
	public ResponseEntity<Summary> saveKids(@RequestBody ChildRequest kids)
	{
		Long caseNumb = service.saveKidsDetails(kids);
		   
		    
		     Summary summary = service.getSummary(caseNumb);
		return new ResponseEntity<>(summary,HttpStatus.CREATED);
	}
	
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
	
	@PostMapping("/saveEducation")
	public ResponseEntity<Long> saveEducation(@RequestBody EducationDetails details)
	{
		return new ResponseEntity<Long>(service.saveEducationDetails(details),HttpStatus.OK);
	}
	
	@PostMapping("/saveIncome")
	public ResponseEntity<Long> saveIncome(@RequestBody IncomeDetails income)
	{
		return new ResponseEntity<Long>(service.saveIncomeDetails(income),HttpStatus.CREATED);
	}
	
	@PostMapping("/saveSelection")
	public ResponseEntity<Long> savePlanSelection(@RequestBody PlanSelection selection)
	{
		return new ResponseEntity<Long>(service.savePlanSelection(selection),HttpStatus.CREATED);
	}
}
