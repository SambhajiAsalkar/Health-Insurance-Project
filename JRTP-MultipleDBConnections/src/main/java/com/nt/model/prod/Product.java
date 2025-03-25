package com.nt.model.prod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="Multi_DB_Product")
public class Product {
	@Id
	@GeneratedValue
private Integer pid;
	@NonNull
	@Column(length=25)
	private String pname;
	@NonNull
	@Column
	private Double price;
	@NonNull
	@Column
	private Double qty;
	@Column(length=20)
	@NonNull
	private String vendor;
	
}
