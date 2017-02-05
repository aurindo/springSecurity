package com.springcookbook.dao;

import java.util.List;

import com.springcookbook.model.Role;
import com.springcookbook.model.User;

public interface RoleDAO {
	
	List<String> findRolesByUser(String username);

	List<Role> findAllRoles();
	
	void saveUserRole(User user, Role role);
	
	void removeAllRolesByUSer(User user);

}
