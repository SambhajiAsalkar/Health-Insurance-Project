package com.sa.service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.sa.EmailUtils.EmailUtil;
import com.sa.Entity.UserMaster;
import com.sa.bindings.ActivateUser;
import com.sa.bindings.LoginCredential;
import com.sa.bindings.UserAccount;
import com.sa.commons.Constants;
import com.sa.repository.IUserService;
import com.sa.repository.UserRepo;



@Component
public class IUserServiceImpl implements IUserService {

	@Autowired
	private UserRepo URepo;
	@Autowired
	private EmailUtil util;
	
	
	@Override
	public boolean registerUser(UserAccount acc) throws Exception{
		String filename="src/main/resources/Register_User_File.txt";
		String subject="Temporary Password............!";
		UserMaster master=new UserMaster();
		BeanUtils.copyProperties(acc, master);
		 
		String randomString = RandomStringUtils.randomAlphanumeric(6);     
		master.setPassword(randomString); 
		master.setAccStatus("INACTIVE");
		UserMaster saved = URepo.save(master);	
		String msgBody = readMailMsg(filename,master.getFullname(),master.getPassword());
		util.sendMail(msgBody,subject,master.getEmail());
		return saved.getUserId()!=null;
	}

	
	private String readMailMsg(String filename,String fullname,String pass)  
	{
		String url="https://www.naukri.com";
		String body=null;
		try {
			FileReader reader=new FileReader(filename);
			BufferedReader bfr=new BufferedReader(reader);
			StringBuffer sb=new StringBuffer();
			          String line=bfr.readLine();
			          while(line!=null) 
			          {
			        	  sb.append(line);
			        	  sb.append("\n");
			        	  line=bfr.readLine();
			          }
			          bfr.close();
			          body=sb.toString();
			         body=body.replace("${Full-Name}",fullname);
			         body=body.replace("${Pass}",pass);
			         body=body.replace("${URL}",url);
			         body = body.replace("\n", "<br>");
			          
		} catch (Exception e) {e.printStackTrace();}
		
		return body;
	}
	@Override
	public boolean activateUser(ActivateUser user) throws Exception{
		/*UserMaster master = URepo.findByEmailAndPassword(user.getEmail(),user.getTempPass());
		if(master==null) 
		{
			return false;
		}
		else
			master.setPassword(user.getConfirmPass());
		    master.setAccStatus("active");
		    URepo.save(master);
		    return true;*/
		
		UserMaster master=new UserMaster();
		master.setEmail(user.getEmail());
		master.setPassword(user.getTempPass());
		Example<UserMaster> example=Example.of(master);
		List<UserMaster> all = URepo.findAll(example);
		if(all.isEmpty()) 
		{
			return false;
		}
		else
		{
			UserMaster userMaster = all.get(0);
			userMaster.setPassword(user.getConfirmPass());
			userMaster.setAccStatus("active");
			URepo.save(userMaster);
			return true;
		}
		
			
	}

	@Override
	public List<UserAccount> showAllUsers() throws Exception{
		List<UserMaster> all = URepo.findAll();
		List<UserAccount> users=new ArrayList<>();
		for(UserMaster master :all)
		{
			UserAccount user=new UserAccount();
		BeanUtils.copyProperties(master, user);
		users.add(user);
		
		}
		return users;
	}
	

	@Override
	public UserAccount getUserById(Integer id)throws Exception 
	{
		Optional<UserMaster> opt = URepo.findById(id);
		if(opt.isPresent()) 
		{
			UserAccount user=new UserAccount();
			UserMaster userMaster = opt.get();
			BeanUtils.copyProperties(userMaster, user);
			return user;
		}
		return null;
	}
	@Override
	public boolean deleteUserAccountById(Integer id) throws Exception{
	
		try {

			URepo.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return false;
		
	}

	@Override
	public boolean changeUserStatus(Integer id,String status) throws Exception{
	          Optional<UserMaster> opt = URepo.findById(id);
	          if(opt.isPresent()) 
	          {
	        	 UserMaster userMaster = opt.get(); 
	        	 userMaster.setAccStatus(status);
	        	 URepo.save(userMaster);
	        	 return true;
	          }
	          else
	        	  return false;
	}
	@Override
	public String loginUser(LoginCredential cred) throws Exception
	{
		UserMaster master=new UserMaster();
		Example<UserMaster> example=Example.of(master);
		List<UserMaster> UmList = URepo.findAll(example);
		if(UmList.isEmpty()) 
		{
			return "invalid user";
		} 
		else
		{
			UserMaster userMaster = UmList.get(0);
			  if(userMaster.getAccStatus().equalsIgnoreCase("Active"))
			  {
				  return Constants.LOGIN_SUCCESS;
		       }
			  else 
			  {
				  return "User account not activated";
			  }
		}
		
	}

	
	@Override
	public String forgotPassword(String email) throws Exception
		{  
		String filename="src/main/resources/RecoverPassword.txt";
		UserMaster master = URepo.findByEmail(email);
		String subject="Forgot Password mail";
		String msgBody = readMailMsg(filename,master.getFullname(),master.getPassword());
		util.sendMail(msgBody,subject,master.getEmail());
		return Constants.PASSWORD_RECOVER_SUCCESS;

	}
}
