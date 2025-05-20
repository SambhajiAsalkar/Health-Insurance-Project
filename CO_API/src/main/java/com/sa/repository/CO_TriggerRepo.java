package com.sa.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.Entity.Co_TriggerEntity;


public interface CO_TriggerRepo extends JpaRepository<Co_TriggerEntity, Serializable> {
public List<Co_TriggerEntity> findByTrgStatus(String trgStatus);
public Co_TriggerEntity findByCaseNum(Long caseNum);
}
