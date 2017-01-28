package com.springcookbook.model;

import java.util.Set;

public class User {

	private Long id;
	private String username;
	private String password;
	private Boolean enable;
	private Set<String> roles;

	public User() {}

	public User(Long id, String username, String password, Boolean enable) {
		this.username = username;
		this.password = password;
		this.enable = enable;
	}
	
	public User(Long id, String username, String password, Boolean enable, Set<String> roles) {
		this.username = username;
		this.password = password;
		this.enable = enable;
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

	public Set<String> getRoles() {
		return roles;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
}