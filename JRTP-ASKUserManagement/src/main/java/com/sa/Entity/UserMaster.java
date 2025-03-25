package com.sa.Entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user_master")
@Data
public class UserMaster {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	@Column(name="full_name",length=20)
	private String fullname;
	@Column(name="email",length=30)
	private String email;
	private Long mobile;
	@Column(name="Gender",length=10)
	private String gender;
	@Column(name="dob")
	private LocalDate dob;
	
	@Column(name="id_proof",length=20)
	private Long ssn;
	private String password;
	@Column(name="active_sw")
	private String accStatus;
	
	
	@CreationTimestamp
	@Column(name="createdOn",insertable = true,updatable=false)
	private String createdOn;
	@UpdateTimestamp
	@Column(name="updatedOn",insertable = false,updatable=true)
	private String updatedOn;
	@Column(name="createdBy")
	private String createdBy;
	@Column(name="updatedBy")
	private String updatedBy;
	
	
}
