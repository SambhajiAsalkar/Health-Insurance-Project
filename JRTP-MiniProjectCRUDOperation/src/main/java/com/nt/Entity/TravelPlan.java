package com.nt.Entity;

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

@Entity
@Table(name="Travel_Plan")
@Data
public class TravelPlan {

	@Id
	@Column(name="plan_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer plan_id;
	@Column(name="plan_name",length=30)
	private String plan_name;
	@Column(name="plan_description",length=50)
	private String plan_description;
	@Column (name="plan_min_budget")
	private Double plan_min_budget;
	@Column (name="plane_category_id")
	private Integer plane_category_id;
	
	@Column(name="active_sw",length=15)
	private String active_sw="Active";
	
	@Column(name="created_date",updatable=false)
	@CreationTimestamp
	private LocalDateTime creation_date;
	 @Column(name="updated_date",updatable=true,insertable=false)
	 @UpdateTimestamp
	 private LocalDateTime updation_date;
	 @Column (name="created_by",length=20)
	 private String created_by;
	 @Column(name="updated_by",length=20)
	 private String updated_by;


}
