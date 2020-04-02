package com.demo.spring.springsecuirity.secuirity;

import java.io.Serializable;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenUtil implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 192408452747738719L;
	
	static final String CLAM_KEY_USERNAME = "sub";
	static final String CLAM_KEY_AUDIENCE = "audience";
	static final String CLAM_KEY_CREATED = "created";
	
	@Value("${jwt.secret}")
	private static String secret;
	
	@Value("${jwt.expiration}")
	private long expiration;
	

	public static String getUsernameFromToken(String token) {
		String username = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		}
		catch(Exception e) {
			username = null;
		}
				
		return username;
	}


	private static Claims getClaimsFromToken(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		}
		catch(Exception e) {
			claims = null;
		}
		return claims;
	}


	public boolean validateToken(String authToken, UserDetails userDetails) {
		
		return false;
	}

}
