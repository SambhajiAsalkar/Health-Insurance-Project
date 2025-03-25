package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.Entity.DC_IncomeEntity;


public interface DC_IncomeRepo extends JpaRepository<DC_IncomeEntity, Integer>{

	public DC_IncomeEntity findByCaseNumber(Long caseNumber);
}
