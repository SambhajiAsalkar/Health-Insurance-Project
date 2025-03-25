package com.sa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.Entity.Co_TriggerEntity;
import java.util.List;


public interface CO_TriggerRepo extends JpaRepository<Co_TriggerEntity, Serializable> {
public List<Co_TriggerEntity> findByTrgStatus(String trgStatus);
}
