package com.springcookbook.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springcookbook.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	public UserDAOImpl() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
		return null;
	}

	public List<User> findAll() {
		return null;
	}
	
	public void add(User user) {
	}
	
}
