package com.springcookbook.dao;

import java.util.List;

import com.springcookbook.exception.ObjectNotFoundException;
import com.springcookbook.model.User;

public interface UserDAO {

	User findByUserName(String username) throws ObjectNotFoundException;
	
	List<User> findAll();
	
	User save(User user) throws ObjectNotFoundException;

	void delete(Long userId);

	User findById(Long id);

	void update(User user);
	
}
