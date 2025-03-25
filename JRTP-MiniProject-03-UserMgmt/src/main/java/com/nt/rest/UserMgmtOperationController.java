package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.binding.ActivateUser;
import com.nt.binding.LoginCredential;
import com.nt.binding.RecoverPassword;
import com.nt.binding.UserAccount;
import com.nt.service.IUserMasterServiceImpl;

@RestController
@RequestMapping("/user-api")
public class UserMgmtOperationController {
    @Autowired
	public IUserMasterServiceImpl service;
    
    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestBody UserAccount account)
    {
    	try {
    		String resultMsg = service.registerUser(account);
       return new ResponseEntity<String>(resultMsg,HttpStatus.CREATED);
		} catch (Exception e) {
		   e.printStackTrace();
		   return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		   
		}
    }
    @PostMapping("/activate")
    public ResponseEntity<String> activateUser(@RequestBody ActivateUser user)
    {
    	try {
			String resultMsg = service.activateUserAccount(user);
			return new ResponseEntity<String>(resultMsg,HttpStatus.CREATED);
		} catch (Exception e) {
         e.printStackTrace();
         return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(LoginCredential login)
    {
    	try {
    		String loginUser = service.loginUser(login);
    		return new ResponseEntity<String>(loginUser,HttpStatus.CREATED);
    		
		} catch (Exception e) {
			 e.printStackTrace();
	         return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
				}
   }
    
    @GetMapping("/report")
    public ResponseEntity<?> getAllUser()
    {
    	try {
    		
        return new ResponseEntity<List<UserAccount>>(service.showAllUsers(),HttpStatus.OK);
    		
		} catch (Exception e) {
			 e.printStackTrace();
	         return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
				}
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<?> showUserByUserId(@PathVariable Integer id)
    {
    	try {
			return new ResponseEntity<UserAccount>(service.getUserById(id),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
	         return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @GetMapping("/find/{email}/{name}")
    public ResponseEntity<?> showUserByEmailAndName(@PathVariable String email,@PathVariable String name)
    {

    	try {
			return new ResponseEntity<UserAccount>(service.showUserByEmailAndName(email, name),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
	         return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UserAccount user)
    {

    	try {
    		String updateUser = service.updateUser(user);
			return new ResponseEntity<String>(updateUser,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
	         return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id)
    {
    	try {
			return new ResponseEntity<String>(service.deleteUserById(id),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
	         return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PatchMapping("/changeStatus/{id}/{status}")
    public ResponseEntity<String> changeUserStatus(@PathVariable Integer id,@PathVariable String status)
    {
    	try {
			return new ResponseEntity<String>(service.changeUserStatus(id, status),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
	         return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PostMapping("/recoverPassword")
    public ResponseEntity<String> RecoverPassword(@RequestBody RecoverPassword password)
    {
    	try {
			return new ResponseEntity<String>(service.recoverPassword(password),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
	         return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
