package com.springcookbook.dao;

import java.util.List;

import com.springcookbook.model.User;

public interface UserDAO {

	User findByUserName(String username);
	
	List<String> findRolesByUser(String username);
	
	List<User> findAll();
	
	void add(User user);
	
}
