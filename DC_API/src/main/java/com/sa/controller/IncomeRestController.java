package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.binding.IncomeDetails;
import com.sa.service.DcServiceImpl;

@RestController
@RequestMapping("/DataCollection")
public class IncomeRestController {
	
	@Autowired
	private DcServiceImpl service;
	@PostMapping("/saveIncome")
	public ResponseEntity<Long> saveIncome(@RequestBody IncomeDetails income)
	{
		return new ResponseEntity<Long>(service.saveIncomeDetails(income),HttpStatus.CREATED);
	}
}
