package com.sa.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="DC_Children")
@Data
public class DC_ChildrenEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer childId;
	private LocalDate childDob;
	private Long childSsn;
	private Long caseNum;
	private String name;
	private Integer age;
	
}
