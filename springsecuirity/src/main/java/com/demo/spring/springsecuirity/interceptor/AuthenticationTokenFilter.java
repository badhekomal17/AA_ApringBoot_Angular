package com.demo.spring.springsecuirity.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.spring.springsecuirity.secuirity.JwtTokenUtil;

public class AuthenticationTokenFilter extends OncePerRequestFilter{
	
	@Autowired private UserDetailsService userDetailsService;
	
	@Autowired private JwtTokenUtil jwtTokenUtil;
	
	@Value("${jwt.header}")
	private String tokenHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authToken = request.getHeader(this.tokenHeader);
		if(authToken!= null && authToken.length() > 7){
			authToken = authToken.substring(7);
		}
		
		String username = JwtTokenUtil.getUsernameFromToken(authToken);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication() ==null) {
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
				boolean isValid = jwtTokenUtil .validateToken(authToken,userDetails);
			}
		
	}

}
