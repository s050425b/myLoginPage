package com.jason.demo.googlelogindemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.jason.demo.googlelogindemo.Util.GoogleLoginUtil;
import com.jason.demo.googlelogindemo.Util.JwtUtil;
import com.jason.demo.googlelogindemo.dto.LoginDto;
import com.jason.demo.googlelogindemo.service.LoginService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@GetMapping
	public String loginPage(Model model) {
		return "login";
	}
	
	@PostMapping
	public String postloginPage(Model model) {
		return "login";
	}

	@PostMapping("/google_authen")
	public @ResponseBody String authen(HttpServletResponse response, @RequestParam String googleJwt ) {
		GoogleIdToken googleToken = GoogleLoginUtil.verify(googleJwt);
		if (googleToken == null) {
			return "Invalid";
		}
		Cookie cookie = new Cookie("jwt" , JwtUtil.generateJwtToken(googleToken.getPayload().getSubject()));
		cookie.setPath("/");
		response.addCookie(cookie);
		return "Valid";
	}
	
	
	@PostMapping("/normal_authen")
	public ResponseEntity<String> normalAuthen(@RequestBody LoginDto loginDto, HttpServletResponse response) {
		if (loginService.isUserValid(loginDto.getUsername(), loginDto.getPassword())) {
			Cookie cookie = new Cookie("jwt" , JwtUtil.generateJwtToken(loginDto.getUsername()));
			cookie.setPath("/");
			response.addCookie(cookie);
			return new ResponseEntity<>("Valid", HttpStatus.OK);
		}
		return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
	}
	
	@GetMapping("/is_user_new")
	public @ResponseBody boolean isUserNew(@RequestParam String username) {
		return loginService.isUserNew(username);
		
	}
}
