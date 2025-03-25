package com.sa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.bindings.ActivateUser;
import com.sa.bindings.LoginCredential;
import com.sa.bindings.UserAccount;
import com.sa.commons.AppConfig;
import com.sa.commons.Constants;
import com.sa.service.IUserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserController {

	public Map<String,String> messages;
	public UserController(AppConfig app)
	{
		messages=app.getMessages();
	}
	
	@Autowired
	private IUserServiceImpl service;

	@GetMapping("/report")
	public ResponseEntity<List<UserAccount>> fetchAllUsers()
	{
		try {
		List<UserAccount> users = service.showAllUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
		
		}
		catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.OK);		
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserAccount acc)
	{
		
		try {
		    if (service.registerUser(acc)) {
		        return new ResponseEntity<>(messages.get(Constants.SAVE_SUCCESS), HttpStatus.CREATED);
		    }
		    return new ResponseEntity<>(messages.get(Constants.SAVE_FAILURE), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
		    return new ResponseEntity<>(messages.get("unexpected_error"), HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<UserAccount> showUser(@PathVariable Integer id)
	{
		UserAccount user=null;
		try {
			user = service.getUserById(id);
			return new ResponseEntity<UserAccount>(user,HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<UserAccount>(user,HttpStatus.OK);
			
		}
		
	}
	
	@PostMapping("/activate")
	public ResponseEntity<String> activateUser(@RequestBody ActivateUser user)
	{
		try {
			if(service.activateUser(user)) 
			{
				return new ResponseEntity<String>(messages.get(Constants.ACTIVATION_SUCCESS),HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>(messages.get(Constants.ACTIVATION_FAILURE),HttpStatus.BAD_REQUEST);
			
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/forgotPwd")
	public ResponseEntity<String> recoverPass(String mail)
	{
		try {
			String msg = service.forgotPassword(mail);
			return new ResponseEntity<String>(msg,HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(messages.get(Constants.PASSWORD_RECOVER_FAILURE),HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
	}
	
	@PatchMapping("/changeStatus/{id}/{status}")
	public ResponseEntity<String> changeStatus(@PathVariable Integer id,@PathVariable String status)
	{
		try {
			if(service.changeUserStatus(id, status))
					{

				return new ResponseEntity<String>(messages.get(Constants.STATUS_CHANGE_SUCCESS),HttpStatus.OK);
					}

			return new ResponseEntity<String>(messages.get(Constants.STATUS_CHANGE_FAILURE),HttpStatus.INTERNAL_SERVER_ERROR);
			
		} catch (Exception e) {
			return new ResponseEntity<String>("UNEXPECTED_EXCEPTION",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody LoginCredential cred)
	{
		try {
			String msg = service.loginUser(cred);

			return new ResponseEntity<String>(msg,HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(messages.get(Constants.LOGIN_FAILURE),HttpStatus.INTERNAL_SERVER_ERROR);
}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> removeUserById(@PathVariable Integer id)
	{
		try {
		if(service.deleteUserAccountById(id)) 
		{
		return new ResponseEntity<String>(messages.get(Constants.DELETE_SUCCESS),HttpStatus.OK);
		}
		return new ResponseEntity<String>(messages.get(Constants.DELETE_FAILURE),HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
		catch (Exception e) {
			return new ResponseEntity<String>("UNEXPECTED EXCEPTION.",HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
}
