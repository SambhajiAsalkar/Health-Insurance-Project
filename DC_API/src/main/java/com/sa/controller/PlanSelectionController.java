package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.binding.PlanSelection;
import com.sa.service.DcServiceImpl;

@RestController
@RequestMapping("/DataCollection")
public class PlanSelectionController {
    
	@Autowired
	private DcServiceImpl service;
	
	@PostMapping("/saveSelection")
	public ResponseEntity<Long> savePlanSelection(@RequestBody PlanSelection selection)
	{
		return new ResponseEntity<Long>(service.savePlanSelection(selection),HttpStatus.CREATED);
	}
}
