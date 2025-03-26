package com.bm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bm.commons.AppConfig;
import com.bm.commons.Constant;
import com.bm.entity.Plan;
import com.bm.entity.PlanCategory;
import com.bm.repository.IplanCategoryRepo;
import com.bm.repository.IplanRepo;

import jakarta.transaction.Transactional;

@Component
@Transactional
public class PlanImplService implements Iplan{

	
	private Map<String,String> messages;
	
	@Autowired
	private IplanCategoryRepo catRepo;
	@Autowired
	private IplanRepo planRepo;
	
	public PlanImplService(AppConfig props)
	{
		messages=props.getMessages();
	}
	
	
	@Override
	public String savePlan(Plan plan) {
		planRepo.save(plan);
		if(plan!=null)
		return messages.get(Constant.SAVE_SUCCESS);
		else
			return messages.get(Constant.SAVE_FAILURE);
	}

	@Override
	public List<Plan> showAllPlan() {
		return planRepo.findAll();
	}

	@Override
	public Map<Integer, String> getPlanCategory() {
		List<PlanCategory> lstPlan = catRepo.findAll();
		HashMap<Integer,String> map=new HashMap<>();
		   lstPlan.forEach(category->
		   {
			   map.put(category.getCategoryId(),category.getCategoryName());
		   });
		   return map;
		   		
	}

	@Override
	public String updatePlan(Plan plan) {
		Optional<Plan> opt = planRepo.findById(plan.getPlanId());
		if(opt.isPresent()) 
		{
			planRepo.save(plan);
			return messages.get(Constant.UPDATION_SUCCESS);
		}
		else
		return messages.get(Constant.UPDATION_FAILURE)+ plan.getPlanId();
	}

	@Override
	public String changeActiveStatusById(Integer id, String status) {
		Optional<Plan> opt = planRepo.findById(id);
		if(opt.isPresent()) 
		{
			Plan plan = opt.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return messages.get(Constant.STATUS_CHANGE_SUCCESS);
		}
		else
			return messages.get(Constant.STATUS_CHANGE_FAILURE);
			
	}

	@Override
	public String deletePlanById(Integer id) {
		Optional<Plan> opt = planRepo.findById(id);
		Plan plan = opt.get();
		if(plan!=null) 
		{
			planRepo.deleteById(id);
			return messages.get(Constant.DELETE_SUCCESS);
		}
		else	
			return messages.get(Constant.DELETE_FAILURE);
	}

	@Override
	public Plan getPlaneById(Integer id) {
		Optional<Plan> opt = planRepo.findById(id);
		return opt.isPresent()?opt.get():null;
	}

}
