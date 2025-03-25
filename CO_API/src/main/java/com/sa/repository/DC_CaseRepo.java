package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.Entity.DcCaseEntity;


public interface DC_CaseRepo extends JpaRepository<DcCaseEntity, Long> {
   public DcCaseEntity findByAppId(Integer appId);
}
