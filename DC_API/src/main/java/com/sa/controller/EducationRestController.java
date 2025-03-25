package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.binding.EducationDetails;
import com.sa.service.DcServiceImpl;
@RestController
@RequestMapping("/DataCollection")
public class EducationRestController {
	
	@Autowired
	private DcServiceImpl service;
	
	@PostMapping("/saveEducation")
	public ResponseEntity<Long> saveEducation(@RequestBody EducationDetails details)
	{
		return new ResponseEntity<Long>(service.saveEducationDetails(details),HttpStatus.OK);
	}
}
