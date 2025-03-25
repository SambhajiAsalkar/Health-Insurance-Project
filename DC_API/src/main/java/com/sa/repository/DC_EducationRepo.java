package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.entity.DC_EducationEntity;

import java.io.Serializable;
import java.util.List;


public interface DC_EducationRepo extends JpaRepository<DC_EducationEntity, Serializable>{
public DC_EducationEntity findByCaseNum(Long caseNum);
}
