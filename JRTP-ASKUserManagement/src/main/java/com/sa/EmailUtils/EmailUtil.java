package com.sa.EmailUtils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
@Autowired
private JavaMailSender mailSender;

public Boolean sendMail(String body,String subject,String toMails) 
       {
	  boolean mailSendStatus=true;
         try {
    	        MimeMessage msg=mailSender.createMimeMessage();
                MimeMessageHelper helper=new MimeMessageHelper(msg);
                helper.setCc(toMails);
                helper.setSentDate(new Date());
                helper.setSubject(subject);
                helper.setText(body,true);
                mailSender.send(msg);
                mailSendStatus=true;
	         } catch (Exception e) {
		        e.printStackTrace();
	             }    
	
        
        return mailSendStatus;
        
        
       }
}
