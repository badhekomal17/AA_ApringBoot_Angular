package com.demo.spring.springsecuirity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.spring.springsecuirity.secuirity.JwtAuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired private UserDetailsService userDetailsService;
	@Autowired private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder authenticationManagerBuider) throws Exception{
		authenticationManagerBuider.userDetailsService(this.userDetailsService).passwordEncoder(PasswordEncoder());
		
	} 
	
	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public AuthenticationManager authenticationMangerBean() throws Exception{
		return super.authenticationManager();
	}
}
