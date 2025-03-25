package com.sa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="DC_Cases")
@Data
public class DcCaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long caseNum;
	private Integer appId;
	private Integer planId;
}
