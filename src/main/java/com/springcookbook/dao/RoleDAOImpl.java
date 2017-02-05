package com.springcookbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springcookbook.model.Role;
import com.springcookbook.model.User;

@Repository
public class RoleDAOImpl implements RoleDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<String> findRolesByUser(String username) {
		String query = 
				"SELECT r.name "
				+ "FROM "
				+ 	"users, user_roles as ur, roles r "
				+ "WHERE "
				+ 	"users.username=? "
				+ 	"AND ur.user_id = users.id "
				+   "AND ur.role_id = r.id ";
		List<String> strings = (List<String>) jdbcTemplate.queryForList(
				query, new Object[] { username }, String.class);
		return strings;
	}

	@Override
	public List<Role> findAllRoles() {
		String query = 
				"SELECT * FROM roles";
		List<Role> roleList = jdbcTemplate.query(
				query, new RoleMapper());
		return roleList;
	}
	
	public void removeAllRolesByUSer(User user) {
		String query = "DELETE FROM USER_ROLES WHERE user_id = ?";
		jdbcTemplate.update(query, new Object[] { user.getId() });
	}
	
	@Override
	public void saveUserRole(User user, Role role) {
		String insert = "INSERT INTO USER_ROLES (user_id, role_id) "
				+ "VALUE (?, ?)";
		jdbcTemplate.update(insert, new Object[] {user.getId(), role.getId()});
	}

	private class RoleMapper implements RowMapper<Role> {
		public Role mapRow(ResultSet row, int rowNum) throws SQLException {
			Role role = new Role(
				row.getLong("id"),
				row.getString("name"));
			return role;
		}
	}
}
