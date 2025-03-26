package com.bm.service;

import java.util.List;
import java.util.Map;

import com.bm.entity.Plan;

public interface Iplan {
public String savePlan(Plan plan);
public List<Plan> showAllPlan();
public Map<Integer,String> getPlanCategory();
public String updatePlan(Plan plan);
public String changeActiveStatusById(Integer id,String status);
public String deletePlanById(Integer id);
public Plan getPlaneById(Integer id);
}
