package com.sa.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

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
@Service
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
    
    Long failed=0l;
	  Long success=0l;
	  
	@Override
	public CO_Response processPendingTrgs() {
		
		  
		  CO_Response response=new CO_Response();
		
		List<Co_TriggerEntity> trgList = trgRepo.findByTrgStatus("pending");
		
		
		response.setTotalTrg(Long.valueOf(trgList.size()));
		
	//single threaded logic
		/*
		for(Co_TriggerEntity entity:trgList) 
		{
		 try {
			processTrigger(response, entity);
				success++;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			failed ++;
		}
		}*/
		
	//multithreading logic
		
		ExecutorService executor=Executors.newFixedThreadPool(10);
		ExecutorCompletionService<Object> pool=new ExecutorCompletionService<>(executor);
		
		
		for(Co_TriggerEntity entity:trgList) 
		{
			pool.submit(() ->{				
					try {
						processTrigger(response, entity);
							success++;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						     failed ++;
				}
					return null;
			});
		 
		}
		response.setFailedTrg(failed);
		response.setSuccessTrg(success);
		return response;
	  
		}

	private CitizenAppEntity processTrigger(CO_Response response,Co_TriggerEntity entity) throws Exception
	{
		CitizenAppEntity appEntity=null;
		
		//get Eligibility  data based on caseNum
		  Elige_DetailsEntity eligEntity = eligRepo.findByCaseNum(entity.getCaseNum());
			//get citizen data based on caseNum
		  Optional<DcCaseEntity> opt = caseRepo.findById(entity.getCaseNum());
		  if(opt.isPresent()) 
		  {
			  DcCaseEntity caseEntity = opt.get();
			  Integer appId=caseEntity.getAppId();
			  Optional<CitizenAppEntity> opt2 = appRepo.findById(appId);
		       if(opt2.isPresent()) 
		       {
		    	    appEntity = opt2.get();
		       }
		       
		  }  
		  
		//generate pdf with eligibility details and send pdf to citizen mail
		  generatePdf(eligEntity,appEntity);
			//store pdf to citizen mail
		return appEntity;
	}
	private void generatePdf(Elige_DetailsEntity elig,CitizenAppEntity citizenEntity)throws Exception
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
      
    emailUtils.sendEmailMessage(citizenEntity.getEmail(),sub,body, file);
	 updateTrigger(elig.getCaseNum(),file); 
	}
	   
	 private void updateTrigger(Long caseNum,File file)throws Exception{
  	   Co_TriggerEntity coEntity=trgRepo.findByCaseNum(caseNum);
       byte[] arr=new byte[(byte)file.length()];
       FileInputStream fis=new FileInputStream(file);
       fis.read(arr);
       coEntity.setTrgStatus("Completed");
       trgRepo.save(coEntity);
     fis.close();
     }
      
	}
	


