package com.jason.demo.googlelogindemo.Util;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class JwtUtil {

	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private static final int EXPIRATION_TIME = 10000;

	public static String generateJwtToken(String username) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
		String jwtToken = Jwts.builder()
				// .claim("name", "Jane Doe")
				// .claim("email", "jane@example.com")
				.setSubject(username)
				// .setId(UUID.randomUUID().toString())
				.setIssuedAt(now).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
		return jwtToken;
	}

	public static boolean isValidJwtToken(String jwtToken) {
		try {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
			Date expirationDate = claims.getExpiration();
			return expirationDate.after(new Date());
		} catch (ExpiredJwtException e) {
			// The JWT has expired
			System.out.println(e);
			return false;
		} catch (Exception e) {
			// Other exceptions, such as signature verification failed
			System.out.println(e);
			return false;
		}
	}

	public static String getJwtFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("jwt")) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}
