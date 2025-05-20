package com.sa.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name="Citizen_App")
@Data
public class CitizenAppEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer appId;
private String fullname;
private String email;
private Long phNo;
private String gender;
private String ssn;
private String stateName;
private LocalDate dob;
@CreationTimestamp
private LocalDate createdDate;
@UpdateTimestamp
private LocalDate updatedDate;
private LocalDate createdBy;
private LocalDate updatedBy;

}
