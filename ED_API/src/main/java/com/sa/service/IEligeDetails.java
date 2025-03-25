package com.sa.service;

import com.sa.binding.EligeResponse;

public interface IEligeDetails {

	public EligeResponse determineEligibilityByCaseNumber(Long caseNum);
}
