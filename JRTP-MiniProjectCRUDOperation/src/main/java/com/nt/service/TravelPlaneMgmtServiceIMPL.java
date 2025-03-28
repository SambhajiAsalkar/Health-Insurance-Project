package com.nt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nt.Entity.PlanCategory;
import com.nt.Entity.TravelPlan;
import com.nt.commons.AppConfigProperties;
import com.nt.constants.TravelPlanConstants;
import com.nt.repository.IPlanCategory;
import com.nt.repository.ITravalPlan;
@Component
public class TravelPlaneMgmtServiceIMPL implements ITravelPlaveMgmtService
{
	@Autowired
	private IPlanCategory planRepo;
	
	@Autowired
	private ITravalPlan travelRepo;
	
	Map<String,String> messages;
	public TravelPlaneMgmtServiceIMPL(AppConfigProperties app) 
	{
		messages=app.getMessages();
	}
	
@Override
public Map<Integer, String> getTravelsCategory() 
 {
	List<PlanCategory> list=planRepo.findAll();
	Map<Integer,String> categoriesMap=new HashMap<Integer,String>();
	list.forEach(category->{
		       categoriesMap.put(category.getCategoryId(),category.getCategoryName());
	          }
		);
		
	return categoriesMap;
}
@Override
	public String registerTravelPlane(TravelPlan plan) 
{
		    travelRepo.save(plan);
		return messages.get(TravelPlanConstants.SAVE_SUCCESS)+ plan.getPlan_id();
	}
@Override
public List<TravelPlan> showAllPlans() 
{
	return travelRepo.findAll();
}

@Override
public TravelPlan showTravelPlanById(Integer id) 
{	
	return travelRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Travel Plan not found"));	
}
@Override
public String updateTravelPlan(TravelPlan plane) 
{               
	Optional<TravelPlan> opt = travelRepo.findById(plane.getPlan_id());
	if(opt.isPresent()) {
		travelRepo.save(plane);
		return messages.get(TravelPlanConstants.UPDATE_SUCCESS) + plane.getPlan_id();
	}
	else
		return messages.get(TravelPlanConstants.UPDATE_FAILURE);
				}
@Override
public String deletTravelPlan(Integer id) {
	Optional<TravelPlan> opt = travelRepo.findById(id);
	if(opt.isPresent()) 
	{
		travelRepo.deleteById(id);
		return messages.get(TravelPlanConstants.DELETE_SUCCESS);
	}
	else
		return messages.get(TravelPlanConstants.DELETE_FAILURE);
}
@Override
public String changeTravelPlanStatus(Integer id, String staus) {
	Optional<TravelPlan> opt = travelRepo.findById(id);
	if(opt.isPresent()) 
	{
		TravelPlan plan=opt.get();
		plan.setActive_sw(staus);
		travelRepo.save(plan);
		return messages.get(TravelPlanConstants.STATUS_CHANGE_SUCCESS);
	}
	else
		return messages.get(TravelPlanConstants.STATUS_CHANGE_FAILURE);
}
}
