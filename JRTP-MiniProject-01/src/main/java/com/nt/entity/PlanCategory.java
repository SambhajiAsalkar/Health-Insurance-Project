package com.nt.entity;

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
@Table(name="planCategaory")
@Data
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
