package com.sa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.Entity.DC_ChildrenEntity;

public interface DC_ChildrenRepo extends JpaRepository<DC_ChildrenEntity, Integer>
{
	public List<DC_ChildrenEntity> findByCaseNum(Long caseNum);
  
}
