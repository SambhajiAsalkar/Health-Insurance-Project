package com.sa.repository;

import java.util.List;

import com.sa.bindings.ActivateUser;
import com.sa.bindings.LoginCredential;
import com.sa.bindings.UserAccount;

public interface IUserService {
public boolean registerUser(UserAccount acc) throws Exception;
public boolean activateUser(ActivateUser user)throws Exception;
public List<UserAccount> showAllUsers()throws Exception;
public UserAccount getUserById(Integer id)throws Exception;
public boolean deleteUserAccountById(Integer id)throws Exception;
public boolean changeUserStatus(Integer id,String status)throws Exception;
public String loginUser(LoginCredential cred)throws Exception;
public String forgotPassword(String email)throws Exception;


}
