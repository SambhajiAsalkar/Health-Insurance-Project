package com.nt.testController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class DemoRestController {

	Logger logger=LoggerFactory.getLogger(DemoRestController.class);
	@GetMapping("/welcome")
	public String getWelcomeMsg() 
	{
		logger.info("getWelcomeMsg() execution started");
		String msg="welcome to hyderabad";
		try {
			int i=10/0;
		} catch (Exception e) {
			logger.error("exception occured :"+ e.getMessage());
			// TODO: handle exception
		}
		logger.info("getWelcomeMsg() execution ended");
	   return msg;
	}
	
}
