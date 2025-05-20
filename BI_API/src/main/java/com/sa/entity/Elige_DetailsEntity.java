package com.sa.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="elige_details")
@Data
public class Elige_DetailsEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "case_num")
	private Long caseNum;
	@Column(name = "plan_name")
	private String planName;
	@Column(name = "holder_name")
	private String holderName;
	@Column(name = "holder_ssn")
	private String holderSsn;
	@Column(name = "plan_status")
	private String planStatus;
	@Column(name = "start_date")
	private LocalDate startDate;
	@Column(name = "end_date")
	private LocalDate endDate;
	@Column(name = "benifit_amt")	
	private Double benifitAmt;
	@Column(name = "deniel_reason")	
	private String denielReason;
	@Column(name = "bank_name")	
	private String bankName;
	@Column(name="account_number")
	private Long AccNo;
}
