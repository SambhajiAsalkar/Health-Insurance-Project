package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="user_management")
public class UserMaster {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	@Column(name="name",length=20)
	private String name;
	@Column(name="email",length=30,nullable = false)
	private String email;
	@Column(name="ContactNo")
	private Long mobileNo;
	@Column(name="pass")
	private String password;
	@Column(name="adhar")
	private String adharNo;
	@Column(name="gender",length=10)
	private String gender="female";
	@Column(name="dob")
	private LocalDateTime dob;
	@Column(name="status")
	private String active_sw;
	@CreationTimestamp
	@Column(name="createdOn",insertable = true,updatable = false)
	private LocalDateTime cretedOn;
	@UpdateTimestamp
	@Column(name="updatedOn",insertable=false,updatable = true)
	private LocalDateTime updatedOn;
	@Column(name="createdBy")
	private String createdBy;
	@Column(name="updatedBy")
	private String updatedBy;

}
