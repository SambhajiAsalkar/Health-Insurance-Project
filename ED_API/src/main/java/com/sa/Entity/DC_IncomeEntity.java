package com.sa.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="DC_Income")
@Data
public class DC_IncomeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer incomeID;
	private Long caseNumber;
	private Double empIncome;
	private Double propertyIncome;
}
