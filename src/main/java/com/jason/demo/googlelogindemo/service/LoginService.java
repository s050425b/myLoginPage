package com.jason.demo.googlelogindemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jason.demo.googlelogindemo.dto.UserDto;
import com.jason.demo.googlelogindemo.repository.UserDao;

@Service
public class LoginService {
	@Autowired
	UserDao userDao;

	public UserDto getUserInfo() {
		return new UserDto("Jason", "happy@gmail.com", "http://abc/a/b.jpg");
	}
	
	public boolean isUserValid(String username, String password) {
		return password.equals(userDao.getUserPasswordHashByUsername(username));
	}
	
	public boolean isUserNew(String username) {
		return true;
	}
}
