package com.springcookbook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.springcookbook.component.CustomAuthenticationProvider;
import com.springcookbook.config.security.SecurityContextAccessor;
import com.springcookbook.config.security.SecurityContextAccessorImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Bean
	public AuthenticationTrustResolver authenticationTrustResolver() {
		AuthenticationTrustResolver authenticationTrustResolver = 
				new AuthenticationTrustResolverImpl();
		return authenticationTrustResolver;
	}
	
	@Bean
	public SecurityContextAccessor securityContextAccessor() {
		SecurityContextAccessor securityContextAccessor = 
				new SecurityContextAccessorImpl();
		return securityContextAccessor;
	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) 
			throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	  http.authorizeRequests()
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		.and()
		  .formLogin().loginPage("/login").failureUrl("/login?error")
		  .usernameParameter("username").passwordParameter("password")
		.and()
		  .logout().logoutSuccessUrl("/login?logout")
		.and()
		  .exceptionHandling().accessDeniedPage("/403")
		.and()
		  .csrf();
	}
}
