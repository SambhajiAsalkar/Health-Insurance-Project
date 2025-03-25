package com.nt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nt.commons.AppConfig;
import com.nt.entity.Plan;
import com.nt.entity.PlanCategory;
import com.nt.repo.PlanCategoryRepo;
import com.nt.repo.PlanRepo;
@Component
public class IPlanImpl implements Iplan{
    @Autowired
	private PlanRepo planRepo;
    @Autowired
    private PlanCategoryRepo catRepo;
    
   
    @Override
	public Map<Integer, String> getPlaneCategory() 
	{
		List<PlanCategory> list=catRepo.findAll();
	   Map<Integer,String> map=new HashMap<Integer,String>();
	  list.forEach(category->
	     {
		  map.put(category.getCategoryId(),category.getCategoryName());
	     }
	  );
	  return map;
	}

	@Override
	public boolean savePlane(Plan plan) {
			  Plan saved = planRepo.save(plan); 
		  /*if(saved.getPlanId()!=null) 
		  { return true;
		  } else return false;		
	   */
		  return saved.getPlanId()!= null? true: false;
		
		
	}

	@Override
	public List<Plan> showAllPlane() {
		return planRepo.findAll();
	}

	@Override
	public Plan getPlaneById(Integer id) {
		 Optional<Plan> opt = planRepo.findById(id);
		return  opt.isPresent()?opt.get():null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		Optional<Plan> opt = planRepo.findById(plan.getPlanId());
		
		  if(opt.isPresent())
		  {
			  planRepo.save(plan); 
			  return true; 
			  } 
		  else 
			  return false;
			
	}

	@Override
	public boolean removePlaneById(Integer id) {
		    boolean status=false;
		   
		    	try {
					planRepo.deleteById(id);
					status= true;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    
		    	return status;
		    
	}

	@Override
	public boolean planStatusChange(Integer id, String status) {
		Optional<Plan> opt=planRepo.findById(id);
		if(opt.isPresent()) 
		{
			Plan plan=opt.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;
			}
		return false;
	}
}
