package com.sa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.binding.CO_Response;
import com.sa.service.Co_ServiceImpl;

@RestController
@RequestMapping("/trigger")
public class ProcessTriggers 
{
	@Autowired
	private Co_ServiceImpl service;
	
 @GetMapping("/process")
 public CO_Response processTriggers() 
 {
	 return service.processPendingTrgs();
 }
}
