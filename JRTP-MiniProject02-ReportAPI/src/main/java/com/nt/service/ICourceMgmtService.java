package com.nt.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.nt.model.SearchInputs;
import com.nt.model.SearchResult;

import jakarta.servlet.http.HttpServletResponse;

public interface ICourceMgmtService {

public Set<String> showAllCourseCategories();
public Set<String> showAllTrainingModes();
public Set<String> showAllFaculties();

public List<SearchResult> showCourcesByFilters(SearchInputs input);
public void generatePdfReport(SearchInputs inputs,HttpServletResponse res)throws IOException;
public void generateExcelReport(SearchInputs inputs,HttpServletResponse res) throws IOException;

}
