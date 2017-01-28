package com.springcookbook.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springcookbook.model.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User findByUserName(String username) {
		String sql = 
				"select * "
				+ "from "
				+ 	"users as users "
				+ "where "
				+ 	"users.username=?";
		User user = jdbcTemplate.queryForObject(sql, new Object[] { username },
				new UserMapper());
		return user;
	}

	public List<String> findRolesByUser(String username) {
		String sql = 
				"select roles.role "
				+ "from "
				+ 	"users, user_roles as roles "
				+ "where "
				+ 	"users.username=? "
				+ 	"and roles.user_id = users.id";
		List<String> strings = (List<String>) jdbcTemplate.queryForList(sql, new Object[] { username }, String.class);
		return strings;
	}
	
	public List<User> findAll() {
		//TODO: implements
		return null;
	}

	public void add(User user) {
		//TODO: implements
	}

	private class UserMapper implements RowMapper<User> {
		public User mapRow(ResultSet row, int rowNum) throws SQLException {
			User user = new User(
				row.getLong("id"),
				row.getString("username"),
				row.getString("password"), 
				row.getBoolean("enabled"));

			return user;
		}
	}

}
