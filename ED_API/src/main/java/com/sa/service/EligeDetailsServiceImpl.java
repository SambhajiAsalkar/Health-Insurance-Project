package com.sa.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.sa.Entity.CitizenAppEntity;
import com.sa.Entity.Co_TriggerEntity;
import com.sa.Entity.DC_ChildrenEntity;
import com.sa.Entity.DC_EducationEntity;
import com.sa.Entity.DC_IncomeEntity;
import com.sa.Entity.DcCaseEntity;
import com.sa.Entity.Elige_DetailsEntity;
import com.sa.Entity.PlanEntity;
import com.sa.binding.EligeResponse;
import com.sa.repository.CO_TriggerRepo;
import com.sa.repository.CitizenAppRepo;
import com.sa.repository.DC_CaseRepo;
import com.sa.repository.DC_ChildrenRepo;
import com.sa.repository.DC_EducationRepo;
import com.sa.repository.DC_IncomeRepo;
import com.sa.repository.EligeRepository;
import com.sa.repository.PlanRepo;

public class EligeDetailsServiceImpl implements IEligeDetails{

	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private DC_IncomeRepo incomeRepo;
	
	@Autowired
	private DC_CaseRepo caseRepo;
	
	@Autowired
	private CitizenAppRepo citizenRepo;
	@Autowired
	private DC_ChildrenRepo childRepo;
	@Autowired
	private DC_EducationRepo eduRepo;
	@Autowired
	private EligeRepository eligRepo;
	@Autowired
	
	private CO_TriggerRepo trgRepo;
	@Override
	public EligeResponse determineEligibilityByCaseNumber(Long caseNum) 
	{
		
		String planName=null;
		Integer age=null;
		
		Integer planId=null;
		
		Optional<DcCaseEntity> opt = caseRepo.findById(caseNum);
		if(opt.isEmpty()) 
		{
			   planId = opt.get().getPlanId();
		}
		
		Optional<PlanEntity> opt1 = planRepo.findById(planId);
		   
		if(opt1.isPresent())
			{
			  planName= opt1.get().getPlanName();
			}
		
		
       Optional<CitizenAppEntity> CtznEntity = citizenRepo.findById(caseNum);
		
       if(CtznEntity.isPresent()) 
       {
    	   LocalDate dob = CtznEntity.get().getDob();
    	   LocalDate now = LocalDate.now();
    	    age= Period.between(dob, now).getYears(); 
       }
       
       EligeResponse eligeResponse = executePlanConditions(planName,age,caseNum);
       Elige_DetailsEntity entity=new Elige_DetailsEntity();
		 BeanUtils.copyProperties(eligeResponse, entity);
		 
		 entity.setCaseNum(caseNum);
		 entity.setHolderName(CtznEntity.get().getFullname());
		 entity.setHolderSsn(CtznEntity.get().getSsn());
		 eligRepo.save(entity);
		 
		 Co_TriggerEntity trg=new Co_TriggerEntity();
		 trg.setCaseNum(caseNum);
		 trg.setTrgStatus("pending");
		 trgRepo.save(trg);
		return eligeResponse;
	}
	
	
	private EligeResponse executePlanConditions(String planName,Integer age,Long caseNum) 
	{
		EligeResponse response=new EligeResponse();
		response.setPlanName(planName);
	
		DC_IncomeEntity income = incomeRepo.findByCaseNumber(caseNum);
		 Double empIncome = income.getEmpIncome();
		
		 if("SNAP".equals(planName)) 
		  {
			if(empIncome<=300) 
			{
				response.setPlanStatus("Aproved");
			}
			else
			  {
				response.setPlanStatus("Deniend");
			    response.setDenielReason("Citizen having high income");
			  }
			}
		else if("CCAP".equals(planName)) 
		 {
			List<DC_ChildrenEntity> childs = childRepo.findByCaseNum(caseNum);
			int size = childs.size();
			for(DC_ChildrenEntity child:childs) 
			{
			  if(empIncome<=300 && size!=0 && child.getAge()<16) 
			    {
				 response.setPlanStatus("Aproved");
			    }
			  else
			  {
				 response.setPlanStatus("Deniend");
			     response.setDenielReason("not satisfied bussiness rules");
			  }
		    }  
		 }
		else if("Medicare".equals(planName))
		{
			if(age>65) 
			{
				response.setPlanStatus("Aproved");
			}
			else
			  {
				 response.setPlanStatus("Deniend");
			     response.setDenielReason("");
			  }	
		}
		else if("Medicaid".equals(planName)) 
		{
			Double propertyIncome = income.getPropertyIncome();
			if(empIncome<=300 && propertyIncome==0) 
			{
				response.setPlanStatus("Aproved");
			}
			else
			  {
				 response.setPlanStatus("Deniend");
			     response.setDenielReason("High Income");
			  }
		}
		else if("NJW".equals(planName))
		{
			DC_EducationEntity byCaseNum = eduRepo.findByCaseNum(caseNum);
			Integer graduationYear = byCaseNum.getGraduationYear();
			Integer now = LocalDate.now().getYear();
			if(income.getEmpIncome()<=0 && graduationYear < now) 
			{
					response.setPlanStatus("Aproved");
			}
			else
			{
				response.setPlanStatus("denied");
				response.setDenielReason("Rules Not Satisfied");
			}
		}
		 //setting dummy data
		if(response.getPlanStatus().equals("Aproved")) {
		 response.setStartDate(LocalDate.now());
		 response.setEndDate(LocalDate.now().plusMonths(6));
		 response.setBenifitAmt(350.00);
		}
		return response;
    }

}
