package com.springcookbook.model;

import java.util.List;
import java.util.Set;

public class User {

	private Long id;
	private String username;
	private String password;
	private Boolean enable;
	private List<String> roles;

	public User() {}

	public User(Long id, String username, String password, Boolean enable) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enable = enable;
	}
	
	public User(Long id, String username, String password, 
			Boolean enable, List<String> roles) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.roles = roles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}