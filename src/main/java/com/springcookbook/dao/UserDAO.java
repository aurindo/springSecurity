package com.springcookbook.dao;

import java.util.List;

import com.springcookbook.model.User;

public interface UserDAO {

	User findByUserName(String username);
	
	List<User> findAll();
	
	User save(User user);

	void delete(Long userId);

	User findById(Long id);

	void update(User user);
	
}
