package com.sa.service;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.sa.Entity.CitizenAppEntity;
import com.sa.Entity.Co_TriggerEntity;
import com.sa.Entity.DcCaseEntity;
import com.sa.Entity.Elige_DetailsEntity;
import com.sa.binding.CO_Response;
import com.sa.repository.CO_TriggerRepo;
import com.sa.repository.CitizenAppRepo;
import com.sa.repository.DC_CaseRepo;
import com.sa.repository.EligeRepository;
import com.sa.utils.EmailUtils;

public class Co_ServiceImpl implements Co_Service{

	@Autowired
	private CO_TriggerRepo trgRepo;
	@Autowired
	private EligeRepository eligRepo;
	
	@Autowired
	private DC_CaseRepo caseRepo;
	
	@Autowired
	private CitizenAppRepo appRepo;
	
	@Autowired
    private EmailUtils emailUtils;	

    @Autowired
   private Environment env;
	@Override
	public CO_Response processPendingTrgs() {

		CitizenAppEntity appEntity=null;
		List<Co_TriggerEntity> trgList = trgRepo.findByTrgStatus("pending");
		for(Co_TriggerEntity entity:trgList) 
		{
			Long caseNum  = entity.getCaseNum();
			//get Eligibility  data based on caseNum
			  Elige_DetailsEntity eligEntity = eligRepo.findByCaseNum(caseNum);
				//get citizen data based on caseNum
			  Optional<DcCaseEntity> opt = caseRepo.findById(caseNum);
			  if(opt.isPresent()) 
			  {
				  DcCaseEntity caseEntity = opt.get();
				  Optional<CitizenAppEntity> opt2 = appRepo.findById(caseEntity.getAppId());
			       if(opt2.isPresent()) 
			       {
			    	    appEntity = opt2.get();
			    	   
			       }
			       
			  }
			//generate pdf with eligibility details and send pdf to citizen mail
			  generatePdf(eligEntity,appEntity);
				//store pdf to citizen mail
			  
			  
			}
		return null;
		
		
		
		  
		}

	private void generatePdf(Elige_DetailsEntity elig,CitizenAppEntity citizenEntity)
	{
        Document document=new Document(PageSize.A4);
        File file=new File(elig.getCaseNum()+".pdf");
        FileOutputStream fos=null ;
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PdfWriter.getInstance(document,fos);
		document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Eligibility Report", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(70f);
        table.setWidths(new float[] {3.5f, 1.5f,3.5f, 3.0f, 3.0f, 1.5f,3.5f});
        table.setSpacingBefore(2.0f);
         
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(5);
         
        font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.BLACK);
         
        cell.setPhrase(new Phrase("Citizen Name", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Plane Status", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Plan Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Plan Start date", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Plan End date", font));
        table.addCell(cell); 
        
        cell.setPhrase(new Phrase("benifit ammount", font));
        table.addCell(cell); 
        
        cell.setPhrase(new Phrase("deniel reason", font));
        table.addCell(cell); 
        
        
        
      //add data cells to pdfTable
       
    	   table.addCell(citizenEntity.getFullname());
    	   table.addCell(elig.getPlanStatus());
    	   table.addCell(elig.getPlanName());
    	   table.addCell(elig.getStartDate()+" ");
    	   table.addCell(elig.getEndDate()+" ");
    	   table.addCell(elig.getBenifitAmt()+" ");
    	   table.addCell(elig.getDenielReason());
       document.add(table);
       document.close();
       String sub="HIS Eligibility Info";
       String body="HIS Eligibility Info";
       try {
		emailUtils.sendEmailMessage(citizenEntity.getEmail(),sub,body, file);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	       	
	}
	
	
    
  
}


