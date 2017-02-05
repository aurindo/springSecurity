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
import com.springcookbook.dao.RoleDAO;
import com.springcookbook.model.User;

@Service
public class AuthenticationService {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	RoleDAO userRoleDAO;

	public Authentication authentication(String name, String password) {
        User user = userDAO.findByUserName(name);

        Authentication auth = null;
        
        if (password.equals(user.getPassword())) {
        	List<String> roles =
        			userRoleDAO.findRolesByUser(user.getUsername());

        	if (!roles.isEmpty()) {
	            auth = loadAuthorities(name, password, roles);
        	}
        }

        return auth;
	}

	private Authentication loadAuthorities(String name, String password,
			List<String> roles) {
		Authentication auth;
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		roles.stream().forEach(role -> {
			grantedAuths.add(new SimpleGrantedAuthority(role));
		});

		auth = new UsernamePasswordAuthenticationToken(
				name, password, grantedAuths);
		return auth;
	}

}
