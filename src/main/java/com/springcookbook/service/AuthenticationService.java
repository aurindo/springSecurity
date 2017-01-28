package com.springcookbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.springcookbook.dao.UserDAO;
import com.springcookbook.model.User;

@Service
public class AuthenticationService {
	
	@Autowired
	UserDAO userDAO;

	public Authentication authentication(String name, String password) {
        User user = userDAO.findByUserName(name);

        if (password.equals(user.getPassword())) {
        	List<String> roles = userDAO.findRolesByUser(user.getUsername());

            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            roles.stream().forEach(role -> {
            	grantedAuths.add(new SimpleGrantedAuthority(role));
            });

            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
            return auth;
        } else {
            return null;
        }
	}

}
