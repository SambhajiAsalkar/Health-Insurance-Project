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

import com.nt.commons.AppConfig;
import com.nt.commons.ConstantsNames;
import com.nt.entity.Plan;
import com.nt.service.IPlanImpl;

@RestController
@RequestMapping("/plans/api")
public class PlanController {
 
public Map<String,String> messages;
	    public PlanController(AppConfig app) 
	    {
	    	messages=app.getMessages();
	    }
		
	@Autowired
 private IPlanImpl service;
	
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer,String>> showAllCategories()
	{
		 Map<Integer, String> planeCategory = service.getPlaneCategory();
		 return new ResponseEntity<>(planeCategory,HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan)
	{
		boolean isSaved = service.savePlane(plan);
		String responseMsg="";
		if(isSaved) 
		{
		  responseMsg=messages.get("save_success");
		}
		else {
			responseMsg=messages.get("save_failure");
			}
	     return new ResponseEntity<>(responseMsg,HttpStatus.OK);
	 }
	
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> showAllPlans()
	{
		List<Plan> showAllPlane = service.showAllPlane();
		return new ResponseEntity<>(showAllPlane,HttpStatus.OK);
	}
	
	@GetMapping("/plan/{id}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer id)
	{
		Plan planeById = service.getPlaneById(id);
	    return new ResponseEntity<>(planeById,HttpStatus.OK);
	}
	
    @PutMapping("/plan/update")
	public ResponseEntity<?> updatePlan(@RequestBody Plan plan)
	{
    	
	    boolean isUpdated = service.updatePlan(plan);
	    String responseMsg="";
	    if(isUpdated) 
	    {
	    	responseMsg=messages.get(ConstantsNames.UPDATE_SUCCESS);
	    }
	    else
	    	responseMsg=messages.get(ConstantsNames.UPDATE_FAILURE);
	  
	    return new ResponseEntity<>(isUpdated,HttpStatus.CREATED);
	}
    
    @DeleteMapping("/plan/{id}")
	public ResponseEntity<?> deletePlan(@PathVariable Integer id)
	{ 
	    boolean isdeleted = service.removePlaneById(id);
	    String responseMsg="";
	    if(isdeleted) 
	    {
	    	responseMsg=messages.get(ConstantsNames.DELETE_SUCCESS);
	    }
	    else
	    	responseMsg=messages.get(ConstantsNames.DELETE_FAILURE);
	    return new ResponseEntity<>(isdeleted,HttpStatus.OK );
	}
    
    @PutMapping("/status-change/{id}/{status}")
	public ResponseEntity<?> changePlanStatus(@PathVariable Integer id,@PathVariable String status)
	{
	     boolean isStatusChange = service.planStatusChange(id,status);
	     String responseMsg="";
	     if(isStatusChange) 
		    {
		    	responseMsg=messages.get(ConstantsNames.STATUS_CHANGE_SUCCESS);
		    }
		    else
		    	responseMsg=messages.get(ConstantsNames.STATUS_CHANGE_FAILURE);
		    
	    return new ResponseEntity<>(isStatusChange,HttpStatus.OK);
	}
	}
	

