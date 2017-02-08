package com.springcookbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springcookbook.dao.RoleDAO;
import com.springcookbook.dao.UserDAO;
import com.springcookbook.model.Role;
import com.springcookbook.model.User;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	public List<Role> findAllRoles() {
		return roleDAO.findAllRoles();
	}
	
	public List<User> findAllUsers() {
		List<User> userList = userDAO.findAll();
		
		userList.stream().forEach(user -> {
			user = loadRoles(user);
		});
		return userList;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void addUser(User user) throws Exception {
		user.setId(userDAO.save(user).getId());
		saveRoles(user);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void delete(Long userId) {
		User user = userDAO.findById(userId);
		roleDAO.removeAllRolesByUSer(user);
		userDAO.delete(userId);
	}
	
	public User findUserById(Long id) {
		User user = userDAO.findById(id);
		user = loadRoles(user); 
		return user;
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false, rollbackFor=Exception.class)
	public void editUser(User user) throws Exception {
		User userDB = userDAO.findById(user.getId());
		userDB.setEnable(user.getEnable());
		userDB.setPassword(user.getPassword());
		userDB.setUsername(user.getUsername());
		
		userDAO.update(user);
		saveRoles(user);
	}
	
	private User loadRoles(User user) {
		user.setRoles(roleDAO.findRolesByUser(user.getUsername()));
		return user;
	}
	
	private void saveRoles(User user) throws Exception {
		
		if (user.getRoles() == null || user.getRoles().isEmpty()) {
			throw new Exception("Role is required!");
		}

		roleDAO.removeAllRolesByUSer(user);
		List<Role> roles = roleDAO.findAllRoles();
		roles.stream().forEach(role -> {
			if (user.getRoles().contains(role.getName())) {
				roleDAO.saveUserRole(user, role);
			}
		});
		
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
	
}
