package com.jason.demo.googlelogindemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getUserPasswordHashByUsername(String username) {
		String sql = "SELECT password FROM users WHERE username = ?";
		return  this.jdbcTemplate.query(sql, rs -> {
			if (rs.next()) {
				return rs.getString("password");
			}
			return "";
		}, username);
	}
}
