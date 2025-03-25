package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="JRTP_Cource_Details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetails{
	@Id
	@GeneratedValue  //optional coz we are going to select data
	private Integer courceID;
	@Column(length=30)
	private String courceName;
	@Column(length=30)
    private String location;
	@Column(length=30)
	private String courseCategory;
	@Column(length=10)
	private String courseStatus;
	@Column(length=30)
	private String facultyName;
	private Double fee;
	@Column(length=30)
	private String adminName;
	@Column(length=30)
	private String adminContact;
	@Column(length=30)
	private String trainingMode;
	private LocalDateTime startDate;
	@CreationTimestamp
	@Column(insertable = true,updatable = false)
	private LocalDateTime creationDate;
	@UpdateTimestamp
	@Column(insertable = false,updatable = true)
	private LocalDateTime updationDate;
	@Column(length=30)
	private String updatedBy;
	@Column(length=30)
	private String createdBy;
}
