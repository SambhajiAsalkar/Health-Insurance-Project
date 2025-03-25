package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.Entity.TravelPlan;

public interface ITravalPlan extends JpaRepository<TravelPlan, Integer>{

}
