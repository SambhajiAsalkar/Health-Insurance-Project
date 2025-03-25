package com.sa.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.binding.ChildRequest;
import com.sa.binding.EducationDetails;
import com.sa.binding.IncomeDetails;
import com.sa.binding.KidsDetails;
import com.sa.binding.PlanSelection;
import com.sa.binding.Summary;
import com.sa.entity.CitizenAppEntity;
import com.sa.entity.DC_ChildrenEntity;
import com.sa.entity.DC_EducationEntity;
import com.sa.entity.DC_IncomeEntity;
import com.sa.entity.DcCaseEntity;
import com.sa.entity.PlanEntity;
import com.sa.repository.CitizenAppRepo;
import com.sa.repository.DC_CaseRepo;
import com.sa.repository.DC_ChildrenRepo;
import com.sa.repository.DC_EducationRepo;
import com.sa.repository.DC_IncomeRepo;
import com.sa.repository.PlanRepo;
@Service
public class DcServiceImpl implements IDcService{


	@Autowired
	private DC_ChildrenRepo childRepo;
	
	@Autowired
	private DC_IncomeRepo incomeRepo;
	
	@Autowired
	private DC_CaseRepo caseRepo;
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private DC_EducationRepo eduRepo;
	
	@Autowired
	private CitizenAppRepo appRepo;
	
	@Override
	public Long loadCaseNumber(Integer appId) {
		
			Optional<CitizenAppEntity> findByAppId = appRepo.findById(appId);
		if(findByAppId !=null)
		{
			DcCaseEntity entity=new DcCaseEntity();
			entity.setAppId(appId);
			
			DcCaseEntity save = caseRepo.save(entity);
			return save.getCaseNum();
		}
		else
			return 0l;
	}

	@Override
	public Map<Integer,String> getPlanName() {
	
		List<PlanEntity> findAll = planRepo.findAll();
		Map<Integer,String> map=new HashMap<>();
		for(PlanEntity entity:findAll) 
		{
			map.put(entity.getPlanId(),entity.getPlanName());
		}
		return map;
	}

	@Override
	public Long savePlanSelection(PlanSelection selection)
	{
		//DcCaseEntity entity=new DcCaseEntity();
		Optional<DcCaseEntity> opt = caseRepo.findById(selection.getCaseNum());
		if(opt.isPresent()) 
		{
			DcCaseEntity dcCaseEntity = opt.get();
			
			dcCaseEntity.setPlanId(selection.getPlanId());
			dcCaseEntity = caseRepo.save(dcCaseEntity);
			return selection.getCaseNum();
		}
		
		return null;
		
	}

	@Override
	public Long saveKidsDetails(ChildRequest request)
	{ 
		      Long caseNum = request.getCaseNum();
		     List<KidsDetails> kids = request.getKids();
	    for(KidsDetails kid : kids) 
	    {
	    	DC_ChildrenEntity entity=new DC_ChildrenEntity();
		      entity.setCaseNum(caseNum);
	    	BeanUtils.copyProperties(kid, entity);
	    	childRepo.save(entity);  	
	    }
	   // childRepo.saveAll(entities);
		
			return request.getCaseNum();
		
	}

	@Override
	public Long saveEducationDetails(EducationDetails details) {
		
		DC_EducationEntity entity=new DC_EducationEntity();
		BeanUtils.copyProperties(details, entity);
		eduRepo.save(entity);
		if(entity.getCaseNum()!=null) 
		{
			return entity.getCaseNum();
		}
		return null;
	}

	@Override
	public Long saveIncomeDetails(IncomeDetails details) {
		
		DC_IncomeEntity entity=new DC_IncomeEntity();
		BeanUtils.copyProperties(details, entity);
		incomeRepo.save(entity);
		if(entity.getCaseNumber()!=null) 
		{
			return entity.getCaseNumber();
		}
		return null;
	}

	@Override
	public Summary getSummary(Long caseNum) {
		String planName="";
		DC_EducationEntity education= eduRepo.findByCaseNum(caseNum);
		List<DC_ChildrenEntity> kids = childRepo.findByCaseNum(caseNum);	
		DC_IncomeEntity income = incomeRepo.findByCaseNumber(caseNum);
		
		Optional<DcCaseEntity> id = caseRepo.findById(caseNum);
		
		if(id.isPresent()) 
		{
			DcCaseEntity dcCaseEntity = id.get();
			Integer planId = dcCaseEntity.getPlanId();	
			Optional<PlanEntity> plnEntity = planRepo.findById(planId);
			if(plnEntity.isPresent()) 
			{
				planName = plnEntity.get().getPlanName();
				
			}
		
		}
		//set summary Data
		
		Summary summary=new Summary();
		summary.setPlaneNames(planName);
		
		EducationDetails eduDetails=new EducationDetails();
		BeanUtils.copyProperties(education, eduDetails);
		summary.setEducationDetails(eduDetails);
		
		IncomeDetails incomeDetails=new IncomeDetails();
		BeanUtils.copyProperties(income, incomeDetails);
		summary.setIncomeDetails(incomeDetails);
		
		List<KidsDetails> childs=new ArrayList<>();
		for(DC_ChildrenEntity entity:kids)
		{
		    KidsDetails details=new KidsDetails();	
			BeanUtils.copyProperties(entity, details);
			childs.add(details);
		}
		summary.setKidsDetails(childs);
		;
		return summary;
		
	}

}
