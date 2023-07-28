package com.jason.demo.googlelogindemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;

@Controller
public class PageController {
	
	@GetMapping("/home")
	public String toHome(Model model,HttpServletRequest request) {
		return "home";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		return "createAcct";
	}
	
	@GetMapping("/error")
	public String error(Model model) {
		return "error";
	}
}
