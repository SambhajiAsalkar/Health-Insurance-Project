package com.nt.service;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.entity.CourseDetails;
import com.nt.model.SearchInputs;
import com.nt.model.SearchResult;
import com.nt.repository.ICourseDetailsRepo;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
@Service("courceService")
public class ICourseMgmtServiceImpl implements ICourceMgmtService {

	@Autowired
	private ICourseDetailsRepo repo;
	@Override
	public Set<String> showAllCourseCategories() {
		return repo.getUniqueCourseCategories();
	}

	@Override
	public Set<String> showAllTrainingModes() {
		return repo.getTrainingMode();

	}

	@Override
	public Set<String> showAllFaculties() {
		return repo.getUniqueFacultyName();

	}

/* @Override
	 public List<SearchResult> showCourcesByFilters(SearchInputs input) {
		CourseDetails entity=new CourseDetails();
		String category=input.getCourseCategory();
		if(category!=null&&!category.equals("")&& category.length()!=0)
			entity.setCourseCategory(category);
		
		String facultyName=input.getFacultyName();
		if(facultyName!=null&&!facultyName.equals("")&&facultyName.length()!=0)
			entity.setFacultyName(facultyName);

		String trainingMode=input.getTrainingMode();
		if(trainingMode!=null&&!trainingMode.equals("")&&trainingMode.length()!=0)
			entity.setTrainingMode(trainingMode);
		
		LocalDateTime startDate=input.getStartsOn();
		if(startDate!=null)
			entity.setStartDate(startDate);
		Example<CourseDetails> example=Example.of(entity);
		
		 List<CourseDetails> listEntities=repo.findAll(example);
		 List<SearchResult> listResult=new ArrayList();
		 listEntities.forEach(course->{
	     SearchResult result=new SearchResult();
		 BeanUtils.copyProperties(course, result);
		 listResult.add(result);});
		 return listResult;
	}
*/
	@Override
	 public List<SearchResult> showCourcesByFilters(SearchInputs input) {
		CourseDetails entity=new CourseDetails();
		String category=input.getCourseCategory();
		if(StringUtils.hasLength(category))
			entity.setCourseCategory(category);
		
		String facultyName=input.getFacultyName();
		if(StringUtils.hasLength(facultyName))
			entity.setFacultyName(facultyName);
		
		String trainingMode=input.getTrainingMode();
		if(StringUtils.hasLength(trainingMode))
			entity.setTrainingMode(trainingMode);
		
		LocalDateTime startOn=input.getStartsOn();
		if(!ObjectUtils.isEmpty(startOn));
		       entity.setStartDate(startOn);
		       
		Example<CourseDetails> example=Example.of(entity);
		List<CourseDetails> listEntities = repo.findAll(example);
		List<SearchResult> listResults=new ArrayList();
		listEntities.forEach(course->{SearchResult result=new SearchResult();
		BeanUtils.copyProperties(course,result);
		listResults.add(result);
		});
		return listResults;
	}
	@Override
	public void generatePdfReport(SearchInputs inputs, HttpServletResponse res) throws DocumentException, IOException {
		//get search result
		List<SearchResult> listResult=showCourcesByFilters(inputs);
        Document document=new Document(PageSize.A4);
		PdfWriter.getInstance(document,res.getOutputStream());
		document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Search Report of courses", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(10);
        table.setWidthPercentage(70f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f,1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(2.0f);
         
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(5);
         
        font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);
         
        cell.setPhrase(new Phrase("CourseID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("CourseName", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Location", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Fee", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Course Status", font));
        table.addCell(cell); 
        
        cell.setPhrase(new Phrase("TrainingMode", font));
        table.addCell(cell); 
        
        cell.setPhrase(new Phrase("adminContact", font));
        table.addCell(cell); 
        
        cell.setPhrase(new Phrase("StartsDate", font));
        table.addCell(cell); 
        
      //add data cells to pdfTable
        
       listResult.forEach(result->{
    	   table.addCell(String.valueOf(result.getCourceID()));
    	   table.addCell(result.getCourceName());
    	   table.addCell(result.getCourseCategory());
    	   table.addCell(result.getFacultyName());
    	   table.addCell(result.getLocation());
    	   table.addCell(String.valueOf(result.getFee()));
    	   table.addCell(result.getCourseStatus());
    	   table.addCell(result.getTrainingMode());
    	   table.addCell(String.valueOf(result.getAdminContact()));
    	   table.addCell(result.getStartDate().toString());

       });
       document.add(table);
       document.close();
	}

	@Override
	public void generateExcelReport(SearchInputs inputs, HttpServletResponse res) throws IOException {
		//get the search Result
		List<SearchResult> listResult=showCourcesByFilters(inputs);
		//create excell workbook
		HSSFWorkbook workbook=new HSSFWorkbook();
		//create sheet in the workbook
		HSSFSheet sheet1=workbook.createSheet("CourseDetails");
		HSSFRow headerRow=sheet1.createRow(0);
		headerRow.createCell(0).setCellValue("CourseID");
		headerRow.createCell(1).setCellValue("CourseName");
		headerRow.createCell(2).setCellValue("location");
		headerRow.createCell(3).setCellValue("CourseCategory");
		headerRow.createCell(4).setCellValue("FacultyName");
		headerRow.createCell(5).setCellValue("fee");
		headerRow.createCell(6).setCellValue("adminContact");
		headerRow.createCell(7).setCellValue("TrainingMode");
		headerRow.createCell(8).setCellValue("StartsDate");
		headerRow.createCell(9).setCellValue("CourseStatus");
		int i=1;
		for(SearchResult result:listResult) {
			HSSFRow dataRow=sheet1.createRow(i);
			dataRow.createCell(0).setCellValue(result.getCourceID());
			dataRow.createCell(1).setCellValue(result.getCourceName());
			dataRow.createCell(2).setCellValue(result.getLocation());
			dataRow.createCell(3).setCellValue(result.getCourseCategory());
			dataRow.createCell(4).setCellValue(result.getFacultyName());
			dataRow.createCell(5).setCellValue(result.getFee());
			dataRow.createCell(6).setCellValue(result.getAdminContact());
			dataRow.createCell(7).setCellValue(result.getTrainingMode());
			dataRow.createCell(8).setCellValue(result.getStartDate());
			dataRow.createCell(9).setCellValue(result.getCourseStatus());
		i++;
		}
		ServletOutputStream outputStream=res.getOutputStream();
	//write the workbook data to response object using the above stream
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
	}

}
