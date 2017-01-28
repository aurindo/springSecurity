package com.springcookbook.model;

public class User {

	private String username;
	private String password;
	private Boolean enable;

	public User() {
	}

	public User(String username, String password, Boolean enable) {
		this.username = username;
		this.password = password;
		this.enable = enable;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
}