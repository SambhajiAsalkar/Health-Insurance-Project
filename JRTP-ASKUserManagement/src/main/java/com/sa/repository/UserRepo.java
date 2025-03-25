package com.sa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sa.Entity.UserMaster;

public interface UserRepo extends JpaRepository<UserMaster, Integer>{
public UserMaster findByEmail(String str);
public UserMaster findByEmailAndPassword(String str,String Str);
}
