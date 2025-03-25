package com.sa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.entity.CitizenAppEntity;

public interface CitizenAppRepo extends JpaRepository<CitizenAppEntity, Serializable>{

	
}
