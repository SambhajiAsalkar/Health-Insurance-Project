package com.sa.service;

import java.util.Map;

import com.sa.binding.ChildRequest;
import com.sa.binding.EducationDetails;
import com.sa.binding.IncomeDetails;
import com.sa.binding.PlanSelection;
import com.sa.binding.Summary;

public interface IDcService {
	
public Long loadCaseNumber(Integer appId);
public Map<Integer,String> getPlanName();
public Long savePlanSelection(PlanSelection selection);
public Long saveKidsDetails(ChildRequest details);
public Long saveEducationDetails(EducationDetails details);
public Long saveIncomeDetails(IncomeDetails details);
public Summary getSummary(Long caseNum);
}
