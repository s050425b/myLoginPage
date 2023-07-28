package com.jason.demo.googlelogindemo.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.jason.demo.googlelogindemo.Util.GoogleLoginUtil;
import com.jason.demo.googlelogindemo.Util.JwtUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class FilterConfig implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String cookieValue = JwtUtil.getJwtFromCookie(req);
		boolean isJwtValid = JwtUtil.isValidJwtToken(cookieValue);
        if (cookieValue == null || cookieValue.isBlank() || !isJwtValid) {
        	res.sendRedirect("/login");
        }
        System.out.println(cookieValue);
		chain.doFilter(request, response);
	}


}