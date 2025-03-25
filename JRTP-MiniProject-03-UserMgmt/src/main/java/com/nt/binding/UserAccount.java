package com.nt.binding;

import java.time.LocalDateTime;

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
private String name;
private String email;
private Long mobileNo;
private String adharNo;
private String gender="female";
private LocalDateTime dob;

}
