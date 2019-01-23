package com.jeanhilger.securex.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity // Add this annotation to an '@Configuration' class to have 
				   // the Spring Security configuration defined in any 'WebSecurityConfigurer' 
				   // or more likely by extending the 'WebSecurityConfigurerAdapter' base 
				   // class and overriding individual methods
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Overriding this method enable to configure the 'HttpSecurity'.
		
		// The 'HttpSecurity' allows configuring web based security for specific http requests.
		// The default behavior is to be applied to all requests, but can be restricted
		// using 'requestMatcher'.
		http
			.authorizeRequests()
				.antMatchers("/", "/home").permitAll() // Specifically, the "/" and "/home" paths are configured 
													   // to not require any authentication. 
				.anyRequest().authenticated() 		   // All other paths must be authenticated.
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		// sets up an in-memory user store with a single user
		UserDetails user =
				User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		
		return new InMemoryUserDetailsManager(user);
		
	}
	
}
