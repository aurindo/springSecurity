package springwebapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.springcookbook.dao.RoleDAO;
import com.springcookbook.dao.UserDAO;
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

}
