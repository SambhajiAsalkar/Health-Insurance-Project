package com.nt.service;

import java.util.List;

import com.nt.binding.ActivateUser;
import com.nt.binding.LoginCredential;
import com.nt.binding.RecoverPassword;
import com.nt.binding.UserAccount;

public interface IUserMaserService {
public String registerUser(UserAccount account) throws Exception;
public String activateUserAccount(ActivateUser user);
public String loginUser(LoginCredential credential);
public List<UserAccount> showAllUsers();
public UserAccount getUserById(Integer id);
public UserAccount showUserByEmailAndName(String email,String name);
public String updateUser(UserAccount user);
public String deleteUserById(Integer id);
public String changeUserStatus(Integer id,String status);
public String recoverPassword(RecoverPassword pass) throws Exception;

}
