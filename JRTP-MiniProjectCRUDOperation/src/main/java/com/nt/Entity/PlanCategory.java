package com.nt.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="plan_category")
@Data
public class PlanCategory {
	@Id
	@SequenceGenerator (name="gen1",sequenceName="category_seq",initialValue=0,allocationSize=1)
	@GeneratedValue(generator = "gen1",strategy=GenerationType.SEQUENCE)
	@Column(name="category_id")
	private Integer categoryId;
	
	@Column(name="category_name",length=30)
	private String categoryName;
	
	@Column(name="active_sw",length=15)
	private String active_sw;
	
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
