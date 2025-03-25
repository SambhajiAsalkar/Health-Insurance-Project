package com.sa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.Entity.DC_EducationEntity;


public interface DC_EducationRepo extends JpaRepository<DC_EducationEntity, Serializable>{
public DC_EducationEntity findByCaseNum(Long caseNum);
}
