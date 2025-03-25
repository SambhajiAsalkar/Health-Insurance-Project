package com.sa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="DC_Education")
@Data
public class DC_EducationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long caseNum;
	private String HighestQualification;
	private Integer graduationYear;
	private String univercity;
}
