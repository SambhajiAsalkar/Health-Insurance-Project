package com.nt.service;

import java.util.List;
import java.util.Map;

import com.nt.entity.Plan;

public interface Iplan {
    public Map<Integer,String> getPlaneCategory();
	public boolean savePlane(Plan plan);
	public List<Plan> showAllPlane();
	public Plan getPlaneById(Integer id);
	public boolean updatePlan(Plan plan);
	public boolean removePlaneById(Integer id);
	public boolean planStatusChange(Integer id,String status);

 }
