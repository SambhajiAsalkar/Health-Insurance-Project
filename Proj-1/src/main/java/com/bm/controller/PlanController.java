package com.bm.controller;

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

import com.bm.service.PlanImplService;
import com.bm.entity.Plan;

@RestController
@RequestMapping("/plans/api")
public class PlanController {
@Autowired
private PlanImplService service;


@GetMapping("/categories")
public ResponseEntity<Map<Integer,String>> showAllCategories()
{
	 Map<Integer, String> planeCategory = service.getPlanCategory();
	 return new ResponseEntity<>(planeCategory,HttpStatus.OK);
}

@PostMapping("/save")
public ResponseEntity<String> savePlan(@RequestBody Plan plan)
{
	String msg =service.savePlan(plan);
     return new ResponseEntity<>(msg,HttpStatus.OK);
 }

@GetMapping("/plans")
public ResponseEntity<List<Plan>> showAllPlans()
{
	List<Plan> showAllPlane = service.showAllPlan();
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
	
    String msg = service.updatePlan(plan);
    
    return new ResponseEntity<>(msg,HttpStatus.CREATED);
}

@DeleteMapping("/plan/{id}")
public ResponseEntity<?> deletePlan(@PathVariable Integer id)
{ 
    String msg = service.deletePlanById(id);

     return new ResponseEntity<>(msg,HttpStatus.OK );
}

@PutMapping("/status-change/{id}/{status}")
public ResponseEntity<?> changePlanStatus(@PathVariable Integer id,@PathVariable String status)
{
     String msg = service.changeActiveStatusById(id,status);	    
    return new ResponseEntity<>(msg,HttpStatus.OK);
}
}
