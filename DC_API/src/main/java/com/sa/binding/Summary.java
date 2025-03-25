package com.sa.binding;

import java.util.List;

import lombok.Data;

@Data
public class Summary {

	private IncomeDetails incomeDetails;
	private EducationDetails educationDetails;
	private List<KidsDetails> kidsDetails;
	private String planeNames;
	
}
