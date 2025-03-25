package com.nt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
