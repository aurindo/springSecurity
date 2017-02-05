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

	@Override
	public User findByUserName(String username) {
		String query = 
				"SELECT * "
				+ "FROM "
				+ 	"users AS users "
				+ "WHERE "
				+ 	"users.username=?";
		User user = jdbcTemplate.queryForObject(query, new Object[] { username },
				new UserMapper());
		return user;
	}

	@Override
	public List<User> findAll() {
		String query = "SELECT * FROM USERS";
		
		List<User> userList = jdbcTemplate.query(
				query, new UserMapper());
		return userList;	
	}

	@Override
	public User save(User user) {
		String insert = "INSERT INTO USERS (username, password, enabled) "
				+ "VALUE (?, ?, ?)";
		jdbcTemplate.update(insert, new Object[] {
				user.getUsername(), user.getPassword(), user.getEnable()});
		
		return this.findByUserName(user.getUsername());
	}
	
	@Override
	public void delete(Long userId) {
		String query = "DELETE FROM USERS WHERE ID = ?";
		jdbcTemplate.update(query, new Object[] { userId });
	}

	@Override
	public User findById(Long id) {
		String query = 
				"SELECT * "
				+ "FROM "
				+ 	"users AS users "
				+ "WHERE "
				+ 	"users.id=?";
		User user = jdbcTemplate.queryForObject(query, new Object[] { id },
				new UserMapper());
		return user;	
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

	@Override
	public void update(User user) {
		String query = 
				"UPDATE USERS "
				+ "SET "
				+ 	"username = ?, password = ?, enabled = ? "
				+ "WHERE "
				+ 	"id = ?";
		jdbcTemplate.update(query, 
				new Object[] { 
					user.getUsername(), 
					user.getPassword(), 
					user.getEnable(),
					user.getId()});
	}

}
