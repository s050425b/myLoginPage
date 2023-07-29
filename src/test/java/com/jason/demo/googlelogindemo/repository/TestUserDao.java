package com.jason.demo.googlelogindemo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUserDao {

	@Autowired
	UserDao userDao;
	
	@Test
	public void testGetPasswordHash() {
		assertEquals("password", this.userDao.getUserPasswordHashByUsername("jasondemo"));
	}
}
