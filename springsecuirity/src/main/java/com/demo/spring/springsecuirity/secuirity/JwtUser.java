package com.demo.spring.springsecuirity.secuirity;

import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser implements UserDetails{
	
	private final long id;
	private final String username;
	private final String password;

}
