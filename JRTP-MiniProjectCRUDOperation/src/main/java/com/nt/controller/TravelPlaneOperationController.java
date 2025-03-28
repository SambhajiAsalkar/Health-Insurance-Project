package com.nt.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Entity.TravelPlan;
import com.nt.service.TravelPlaneMgmtServiceIMPL;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/travelplan/api")
@Slf4j
public class TravelPlaneOperationController {
 @Autowired
 private TravelPlaneMgmtServiceIMPL service;

 @PostMapping("/register")
 public ResponseEntity<String> registerTravelPlan(@RequestBody TravelPlan plan)
 {
	 log.trace("at the beginning of register method");
	 try {
		 log.trace("at registration activity");
	 String msg=service.registerTravelPlane(plan);
	 log.trace("registration completed successfully");
	 
	 return new ResponseEntity<String>(msg,HttpStatus.CREATED);
	 }
	 catch(Exception e) 
	 {
		 e.printStackTrace();
		 log.error("problem in registration"+ e.getMessage());
		 return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
	 }
 }
 
 @GetMapping("/categories")
 public ResponseEntity<?> showTravelCategory()
 {
	 try {
		Map<Integer, String> travelsCategory = service.getTravelsCategory();
		return new ResponseEntity<Map<Integer,String>>(travelsCategory, HttpStatus.OK);
	} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
	}
 }
 
 @GetMapping("/all")
 public ResponseEntity<?> showAllPlans()
 {
	 try {
		List<TravelPlan> list = service.showAllPlans();
		return new ResponseEntity<List<TravelPlan>>(list,HttpStatus.OK);
	} catch (Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
 }
 
 @GetMapping("/find/{id}")
 public ResponseEntity<?> getPlanById(@PathVariable Integer id)
 {
	 try {
		TravelPlan plan= service.showTravelPlanById(id);
		return new ResponseEntity<TravelPlan>(plan,HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
 }
 
 @PutMapping("/update")
 public ResponseEntity<?> updatePlane(@RequestBody TravelPlan plane)
 {
	 try {
		String msg = service.updateTravelPlan(plane);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	} catch (Exception e) {
		// TODO: handle exception
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
 }
 
 @DeleteMapping("/delete/{id}")
 public ResponseEntity<String> removePlanById(@PathVariable Integer id)
 {
	 try {
		String msg=service.deletTravelPlan(id);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
 }
 @PutMapping("/staus/{id}/{status}")
 public ResponseEntity<String> changeActiveStatus(@PathVariable Integer id,@PathVariable String status)
 {
	 try {
		String msg = service.changeTravelPlanStatus(id, status);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);	}
	 
 }
 
 
}

