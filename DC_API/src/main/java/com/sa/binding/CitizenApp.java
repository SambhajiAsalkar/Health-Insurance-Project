package com.sa.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CitizenApp {
	private String name;
	private String email;
	private String gender;
	private String ssn;
	private LocalDate dob;
	private Long phNo;
}
