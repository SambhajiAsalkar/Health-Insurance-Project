package com.sa.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Plan_Master")
@Data
public class PlanEntity {
	@Id
	@GeneratedValue
	@Column(name="planId")
private Integer planId;
	@Column(name="planName")
	private String planName;
@Column(name="planStartDate")
private LocalDate startDate;
@Column(name="planEndDate")
private LocalDate endDate;
@Column(name="activeSw")
private String activeSw;
@Column(name="categoryId")
private Integer categoryId;
@Column(name="createdBy")
private String createdBy;
@Column(name="updatedBy")
private String updatedBy;
@Column(name="createdDate",updatable = false)
@CreationTimestamp
private LocalDateTime createdDate;
@Column(name="updatedDate",insertable=false)
@UpdateTimestamp
private LocalDateTime updatedDate;
}
