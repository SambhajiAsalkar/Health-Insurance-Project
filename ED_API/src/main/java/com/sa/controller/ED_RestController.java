package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.binding.EligeResponse;
import com.sa.service.EligeDetailsServiceImpl;

@RestController
@RequestMapping("/Eligibility")
public class ED_RestController {
	@Autowired
	private EligeDetailsServiceImpl service;
	
	@GetMapping("/caseNum/{caseNum}")
	public EligeResponse determineEligibility(@PathVariable Long caseNum) 
	{
		EligeResponse eligeResponse = service.determineEligibilityByCaseNumber(caseNum);
		return eligeResponse;
	}
	
	
	

}
