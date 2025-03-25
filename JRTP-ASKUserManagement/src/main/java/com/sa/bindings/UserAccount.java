package com.sa.bindings;

import java.time.LocalDate;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Integer user_id;
	private String fullname;
	private String email;
	private LocalDate dob;
	private Long mobile;
	private Long ssn;
	private String gender="female";
}
