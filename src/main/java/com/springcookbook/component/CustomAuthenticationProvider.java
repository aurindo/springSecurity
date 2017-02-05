package com.springcookbook.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.springcookbook.service.AuthenticationService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	AuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) 
    		throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        return authenticationService.authentication(name, password);
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
