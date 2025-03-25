package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.entity.DC_IncomeEntity;
import java.util.List;


public interface DC_IncomeRepo extends JpaRepository<DC_IncomeEntity, Integer>{

	public DC_IncomeEntity findByCaseNumber(Long caseNumber);
}
