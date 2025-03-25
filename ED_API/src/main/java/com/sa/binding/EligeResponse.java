package com.sa.binding;

import java.time.LocalDate;

import lombok.Data;
@Data
public class EligeResponse {
	private Long caseNum;
	private String planName;
	private String planStatus;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double benifitAmt;
	private String denielReason;
}
