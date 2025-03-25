package com.sa.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sa.binding.CitizenApp;
import com.sa.entity.CitizenAppEntity;
import com.sa.exception.InvalidSSNException;
import com.sa.repository.CitizenAppRepo;

@Service
public class ArServiceImpl implements ArService{
     @Autowired
	private CitizenAppRepo repo;
     
     
     @Value("${ar.ssa-web.url}")
     private String url;
     
     @Value("${ar.state}")
     private String targetState;
     
	@Override
	public Integer createApplication(CitizenApp app) throws InvalidSSNException {
	//rest call to ssa web
		//String url="http://localhost:9090/ssa-web-api/find/{ssn}";
		/*RestTemplate rt=new RestTemplate();
		ResponseEntity<String> forEntity = rt.getForEntity(url,String.class,app.getSsn());
		*/
		
		WebClient client=WebClient.create();
		String StateName=client.get()
				                .uri(url,app.getSsn())
				                .retrieve()
				                .bodyToMono(String.class)
				                .block();
		
		 //String StateName = forEntity.getBody();
		 if(StateName.equalsIgnoreCase(targetState)) 
		 {
			 CitizenAppEntity entity=new CitizenAppEntity();
			 BeanUtils.copyProperties(app, entity);
			 entity.setStateName(StateName);
			  
			 CitizenAppEntity save = repo.save(entity);
			 return save.getAppId();
		 }
		 return 0;
	}

}
