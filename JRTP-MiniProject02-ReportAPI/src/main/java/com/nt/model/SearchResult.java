package com.nt.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {
	private Integer courceID;
	private String courceName;
    private String location;
	private String courseCategory;
	private String courseStatus;
	private String facultyName;
	private Double fee;
	private String trainingMode;
	private String adminContact;
	private LocalDateTime startDate;
}
