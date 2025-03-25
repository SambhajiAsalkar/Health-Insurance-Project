package com.nt.reppo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.binding.UserAccount;
import com.nt.entity.UserMaster;

public interface IUserMasterRepo extends JpaRepository<UserMaster, Integer>{
  
	public UserMaster findByEmailAndPassword(String email,String password);
	public UserMaster findByNameAndEmail(String name,String email);
	

}
