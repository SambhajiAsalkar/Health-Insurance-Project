package com.sa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.Entity.Elige_DetailsEntity;

public interface EligeRepository extends JpaRepository<Elige_DetailsEntity, Serializable> {

	public Elige_DetailsEntity findByCaseNum(Long caseNum);
}
