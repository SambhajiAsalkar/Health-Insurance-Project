package com.sa.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.entity.Elige_DetailsEntity;

public interface EligeRepository extends JpaRepository<Elige_DetailsEntity, Serializable> {

    List<Elige_DetailsEntity> findByPlanStatus(String planStatus);
}
