package com.sa.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="Elige_Details")
@Data
public class Elige_DetailsEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long caseNum;
	private String planName;
	private String holderName;
	private String holderSsn;
	private String planStatus;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double benifitAmt;
	private String denielReason;
	
}
