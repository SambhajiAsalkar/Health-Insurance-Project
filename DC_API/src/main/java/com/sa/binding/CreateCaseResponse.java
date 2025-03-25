package com.sa.binding;

import java.util.Map;

import lombok.Data;

@Data
public class CreateCaseResponse {

	public Long caseNumber;
	public Map<Integer,String> planeName;
    
}
