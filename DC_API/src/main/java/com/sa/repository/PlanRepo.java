package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.entity.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity, Integer> {

}
