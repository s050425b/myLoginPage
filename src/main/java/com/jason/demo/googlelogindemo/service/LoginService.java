package com.jason.demo.googlelogindemo.service;

import org.springframework.stereotype.Service;

import com.jason.demo.googlelogindemo.dto.UserDto;

@Service
public class LoginService {

	public UserDto getUserInfo() {
		return new UserDto("Jason", "happy@gmail.com", "http://abc/a/b.jpg");
	}
	
	public boolean isUserValid(String username, String password) {
		return true;
	}
	
	public boolean isUserNew(String username) {
		return true;
	}
}
