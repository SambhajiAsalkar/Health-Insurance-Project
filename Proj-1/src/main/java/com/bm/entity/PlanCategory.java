package com.bm.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Plan_Category")
public class PlanCategory {
@Id
@GeneratedValue
@Column(name="categoryId")
private Integer categoryId;
@Column(name="categoryName")
private String categoryName;
@Column(name="activeSw")
private String activeSw;
@Column(name="createdBy")
private String createdBy;
@Column(name="updatedBy")
private String updateddBy;
@Column(name="createdDate",updatable = false)
@CreationTimestamp
private LocalDateTime createdDate;
@Column(name="updatedDate",insertable=false)
@UpdateTimestamp
private LocalDateTime updatedDate;
}
