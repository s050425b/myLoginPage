package com.jason.demo.googlelogindemo.Util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class GoogleLoginUtil {

	private static GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(),  new GsonFactory())
.setAudience(Collections.singletonList("480002319883-a89bli2rjcj3d949ol9s05prl2v0i3db.apps.googleusercontent.com"))
			.build();
	
	public static GoogleIdToken verify(String idTokenString) {
		if (idTokenString != null && !idTokenString.isEmpty()) {
			GoogleIdToken idToken = null;
			try {
				idToken = verifier.verify(idTokenString);
			} catch (Exception e) {
				System.out.println(e);
			}
			return idToken;
		}
		return null;
	}
	

}
