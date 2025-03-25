package com.nt.ms;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.model.SearchInputs;
import com.nt.model.SearchResult;
import com.nt.service.ICourceMgmtService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reporting/api")
public class CourseReportOperationController {
@Autowired
private ICourceMgmtService service;

@GetMapping("/courses")
public ResponseEntity<?> fetchCourseCategory()
{
	try {
  Set<String> courses = service.showAllCourseCategories();
  return new ResponseEntity<Set<String>>(courses,HttpStatus.OK);
}catch (Exception e) {
	return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
}
	}

@GetMapping("/training-mode")
public ResponseEntity<?> fetchTrainingMode()
{
try {
	Set<String> trainingModeInfo = service.showAllTrainingModes();
	return new ResponseEntity<Set<String>>(trainingModeInfo,HttpStatus.OK);
} catch (Exception e) {
	return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
}	
}

@GetMapping("/faculty")
public ResponseEntity<?> fetchFaculties()
{
try {
	Set<String> faculties = service.showAllFaculties();
	return new ResponseEntity<Set<String>>(faculties,HttpStatus.OK);
} catch (Exception e) {
	return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
}	
}

@PostMapping("/search")
public ResponseEntity<?> fetchCoursesByFilter(@RequestBody SearchInputs inputs)
{
try {
	List<SearchResult> list = service.showCourcesByFilters(inputs);
	return new ResponseEntity<List<SearchResult>>(list,HttpStatus.OK);
} catch (Exception e) {
	return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
}	
}

@PostMapping("/all-pdf-report")
public void showPdfReport(@RequestBody SearchInputs inputs,HttpServletResponse res)
{
try {
	res.setContentType("application/pdf");
	res.setHeader("Content-Disposition","attachment;fileName=courses.pdf");
    service.generatePdfReport(inputs, res);
} catch (Exception e) {
e.printStackTrace();
}	
}
@PostMapping("/all-xls-report")
public void showAllExcelReport(SearchInputs inputs,HttpServletResponse res)
{
try {
	res.setContentType("application/vnd.ms-excel");
	res.setHeader("Content-Disposition","attachment;fileName=courses.xls");
    service.generateExcelReport(inputs,res);
    }
catch (Exception e) {
e.printStackTrace();
}	
}
}
