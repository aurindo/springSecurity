package springwebapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.springcookbook.dao.RoleDAO;
import com.springcookbook.dao.UserDAO;
import com.springcookbook.model.Role;
import com.springcookbook.model.User;
import com.springcookbook.service.UserService;

public class UserServiceTest {
	
	private UserService userService = new UserService();
	
	@Mock
	private UserDAO userDAO;
	
	@Mock
	private RoleDAO roleDAO;
	
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Before
	public void init() {
		userService.setUserDAO(userDAO);
		userService.setRoleDAO(roleDAO);
	}
	
	@Test
	public void shouldReturnAllUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(1L, "userA", "userA", true));
		users.add(new User(2L, "userB", "userB", true));
		
		List<String> rolesA = new ArrayList<String>();
		rolesA.add("User");
		
		List<String> rolesB = new ArrayList<String>();
		rolesB.add("User");
		rolesB.add("Admin");

		when(userDAO.findAll()).thenReturn(users);
		when(roleDAO.findRolesByUser(
				users.get(0).getUsername())).thenReturn(rolesA);
		when(roleDAO.findRolesByUser(
				users.get(1).getUsername())).thenReturn(rolesB);
		
		List<User> usersResult = userService.findAllUsers();

		assertEquals(users.size(), usersResult.size());
		assertEquals(rolesB.get(1), users.get(1).getRoles().get(1));
	}
	
	@Test
	public void shouldReturnAllRoles() {
		List<Role> rolesExpected = new ArrayList<Role>();
		rolesExpected.add(new Role("Admin"));
		rolesExpected.add(new Role("User"));
		
		when(roleDAO.findAllRoles()).thenReturn(rolesExpected);
		
		List<Role> roles = userService.findAllRoles();
		
		assertEquals(rolesExpected.size(), roles.size());
		assertEquals(rolesExpected.get(1).getName(), roles.get(1).getName());
	}
	
	@Test
	public void callFindAllRolesReturnEmptyShouldReturnEmpty() {
		List<Role> rolesExpected = new ArrayList<Role>();
		
		when(roleDAO.findAllRoles()).thenReturn(rolesExpected);
		
		List<Role> roles = userService.findAllRoles();
		
		assertEquals(rolesExpected.size(), roles.size());
	}
	
	@Test
	public void shouldReturnAnUniqueUser() {
		User userExpected = new User(1L, "user", "password", true);
		
		when(userDAO.findById(userExpected.getId())).thenReturn(userExpected);
		
		User userReturned = userService.findUserById(userExpected.getId());
		
		assertEquals(userExpected, userReturned);
		assertEquals(userExpected.getUsername(), userReturned.getUsername());
	}
	
	@Test
	public void callFindByIdWhenUserNotFound() {		
		when(userDAO.findById(1L)).thenReturn(null);
		
		User userReturned = userService.findUserById(1L);
		
		Assert.assertNull(userReturned);
	}
	
	@Test
	public void shouldAddUserExecuteSuccessfully() 
			throws Exception {
		
		List<String> rolesStr = new ArrayList<String>();
		rolesStr.add("Admin");
		rolesStr.add("User");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(new Role("Admin"));
		roles.add(new Role("User"));
		
		User userExpected =
				new User(1L, "username", "password", true, rolesStr);
		
		when(userDAO.save(userExpected)).thenReturn(userExpected);
		
		when(roleDAO.findAllRoles()).thenReturn(roles);
		
		userService.addUser(userExpected);
	}
	
	@Test(expected=Exception.class)
	public void addUserWithoutRoleShouldThrowException() throws Exception {
		User userExpected =
				new User(1L, "username", "password", 
						true, new ArrayList<String>());
		
		when(userDAO.save(userExpected)).thenReturn(userExpected);
		
		userService.addUser(userExpected);	
	}
	
	@Test
	public void shouldRemoveUser() {
		
		User userExpected = new User(1L, "user", "password", true);
		
		when(userDAO.findById(userExpected.getId())).thenReturn(userExpected);

		userService.delete(userExpected.getId());
	}

}
