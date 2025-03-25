package com.nt.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.nt.binding.ActivateUser;
import com.nt.binding.LoginCredential;
import com.nt.binding.RecoverPassword;
import com.nt.binding.UserAccount;
import com.nt.entity.UserMaster;
import com.nt.reppo.IUserMasterRepo;
import com.nt.utils.EmailUtils;

@Component
public class IUserMasterServiceImpl implements IUserMaserService{
    @Autowired
	private IUserMasterRepo repo;
    
    @Autowired
    private EmailUtils emailUtils;	

    @Autowired
   private Environment env;
    
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    
    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
    
    private String readEmailMessageBody(String fileName,String fullName,String Pwd) throws Exception
    {
    	String mailBody=null;
    	String url="http://localhost:4041/activate";
    	
    	try (FileReader reader=new FileReader(fileName);
			BufferedReader br= new BufferedReader(reader);
			){
			StringBuffer buffer=new StringBuffer();
			String line=null;
			do 
			{
				line=br.readLine();
				buffer.append(line);
				
			}while(line!=null);
			mailBody=buffer.toString();
			mailBody=mailBody.replace("{Full-name}",fullName);
			mailBody=mailBody.replace("{PWD}",Pwd);
			mailBody=mailBody.replace("{URL}",url);

    	} catch (Exception e) {
            e.printStackTrace();
            throw e;
		}
    	return mailBody;
    }
    
	@Override
	public String registerUser(UserAccount account) throws Exception {
		UserMaster master=new UserMaster();
		BeanUtils.copyProperties(account, master);
		String tempPass=generateRandomString(6);
		master.setPassword(tempPass);
		master.setActive_sw("INACTIVE");
		UserMaster saved = repo.save(master);
		
		//TO DO SEND MAIL
		String subject="User Registration Success";
		String body=readEmailMessageBody(env.getProperty("mailbody.registeruser.location"),account.getName(),tempPass);
		emailUtils.sendEmailMessage(account.getEmail(),subject,body);
		
		return saved!=null?"user is registered with id value "+saved.getUserId()+"check mail for temp password":"problem in user registration";
	}

	@Override
	public String activateUserAccount(ActivateUser user) {
       
		/*UserMaster master=new UserMaster();
		master.setEmail(user.getEmail());
		master.setPassword(user.getTempPassword());
		
		Example example=Example.of(master);
		List<UserMaster> list=repo.findAll(example);
		
		if(list!=null) 
		{
		   UserMaster userMstr = list.get(0);
		   userMstr.setPassword(user.getConfirmPassword());
		   userMstr.setActive_sw("Active");
		   repo.save(userMstr);
		   return "user activated with new password";
		}
		return "User not found";*/
		
		//use finder method
		UserMaster userMstr = repo.findByEmailAndPassword(user.getEmail(), user.getTempPassword());
		if(userMstr==null) 
		{
			return "user not found";
		}
		else 
		{
			userMstr.setPassword(user.getConfirmPassword());
			   userMstr.setActive_sw("Active");
		UserMaster saved = repo.save(userMstr);
		return "user activated with new password";
		}
	}

	@Override
	public String loginUser(LoginCredential credential) {
		UserMaster master=new UserMaster();
		
		BeanUtils.copyProperties(credential, master);
	     Example<UserMaster> example=Example.of(master);
	     List<UserMaster> listUm = repo.findAll(example);
	     if(listUm.size()==0) 
	     {
	    	 return "Invalid credential";
	     }
	     else 
	     {
	    	 UserMaster master1 = listUm.get(0);
	    	 if(master1.getActive_sw().equalsIgnoreCase("active")) 
	    	 {
	    		 return "valid credential & Login Successfull";
	    	 }
	    	 else
	    	   {
	    		 return "user account de-activated";
	    		 }
	     }
	  
	}

	@Override
	public List<UserAccount> showAllUsers() {
		List<UserMaster> list = repo.findAll();
		
		List<UserAccount> list1=new ArrayList<>();
		
		list1=list.stream().map(entity->{
			  UserAccount userAcc=new UserAccount();
			  BeanUtils.copyProperties(entity, userAcc);
		      return userAcc;
		  }).toList();
		/*list.forEach(user->{
		  UserAccount userAcc=new UserAccount();
			//userAcc.setAdharNo(user.getAdharNo());
			//userAcc.setName(user.getName());
			//userAcc.setMobileNo(user.getMobileNo());
			//userAcc.setDob(user.getDob());
			//userAcc.setGender(user.getGender());
			//userAcc.setEmail(user.getEmail());
		
			   BeanUtils.copyProperties(user, userAcc);
		     list1.add(userAcc);
		   });*/
	    
	    
		
		return list1;
	}

	@Override
	public UserAccount getUserById(Integer id) {
		 Optional<UserMaster> opt = repo.findById(id);
		 UserAccount userAcc=null;
		 if(opt.isPresent()) 
		 {
			 userAcc=new UserAccount();
			 BeanUtils.copyProperties(opt.get(),userAcc);
			 return userAcc;
		 }
			return userAcc;
	
		
	}

	

	@Override
	public String deleteUserById(Integer id) {
		Optional<UserMaster> opt = repo.findById(id);
		if(opt.isPresent()) 
		{
			repo.delete(opt.get());
			return "user deleted successfully";
		}
		return "user not found";
	}

	@Override
	public String changeUserStatus(Integer id, String status) {
		Optional<UserMaster> opt = repo.findById(id);
		UserMaster master=null;
		if(opt.isPresent()) 
		{
			master=new UserMaster();
			master.setActive_sw(status);
			return "status saved with id value "+ master.getUserId();
		}
		return "User Not Found";
		
	}

	@Override
	public String recoverPassword(RecoverPassword pass) throws Exception 
	{
		
		UserMaster master=repo.findByNameAndEmail(pass.getName(), pass.getEmail());
		if(master!=null) 
		{
			String pwd=master.getPassword();
			//TO Do send the recover password to mail
			String subject="mail for password recovery";
			String mailBody=readEmailMessageBody(env.getProperty("mailbody.recoverpass.location"),pass.getName(),pwd);
			emailUtils.sendEmailMessage(pass.getEmail(), subject, mailBody);
			return "Your password is:"+master.getPassword();
		}
		return "user and mail not found";
		/*
		List<UserMaster> listUm1 = repo.findAll();
		if(listUm1.size()==0) 
		{
		 return "Invalid credential";
		}
		else 
		{
		 UserMaster master1 = listUm1.get(0);
		 if(master1.getName().equalsIgnoreCase(pass.getName())) 
		 {
			 return "your password is :"+ master1.getPassword();
		 }
		 else
		   {
			 return "Please enter valid credential";
			 }
		}*/
	}

	@Override
	public String updateUser(UserAccount user) {
		
	             UserMaster master = repo.findByNameAndEmail(user.getName(), user.getEmail());
	              if(master!=null)
	             {
	            	 
	             BeanUtils.copyProperties(user, master);
	             repo.save(master);
	             return "user updated successfully";
	             }
	             return "user not found";
	}

	@Override
	public UserAccount showUserByEmailAndName(String email, String name) {
		 UserMaster user = repo.findByNameAndEmail(name, email);
		 if (user == null) {
		        return null; // or throw a custom exception
		    }  
		 UserAccount userAcc=new UserAccount();
		  BeanUtils.copyProperties(user, userAcc);
		  return  userAcc;
	      
	}

}
