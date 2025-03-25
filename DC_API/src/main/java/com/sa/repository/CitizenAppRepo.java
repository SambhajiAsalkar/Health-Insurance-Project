package com.sa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.entity.CitizenAppEntity;
import com.sa.entity.DcCaseEntity;

public interface CitizenAppRepo extends JpaRepository<CitizenAppEntity, Serializable>{
	
	
}
