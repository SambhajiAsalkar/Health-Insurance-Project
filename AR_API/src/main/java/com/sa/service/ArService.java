package com.sa.service;

import com.sa.binding.CitizenApp;
import com.sa.exception.InvalidSSNException;

public interface ArService 
{
 public Integer createApplication(CitizenApp app) throws InvalidSSNException;
 
}
