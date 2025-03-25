package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.Entity.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity, Integer> {

}
