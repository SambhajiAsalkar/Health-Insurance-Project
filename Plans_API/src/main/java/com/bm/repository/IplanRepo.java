package com.bm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bm.entity.Plan;

public interface IplanRepo extends JpaRepository<Plan, Integer>{

}
