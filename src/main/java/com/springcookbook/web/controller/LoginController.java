package com.springcookbook.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springcookbook.config.security.SecurityContextAccessor;

@Controller
public class LoginController {
	
	@Autowired
	private SecurityContextAccessor securityContextAccessor;
	
	private String defaultTargetUrl = "/index";

	@RequestMapping(value = { "/", "/login**" }, method = RequestMethod.GET)
	public String loginPage() {

	    if (securityContextAccessor.isCurrentAuthenticationAnonymous()) {
	    	return "login";
	      } else {
	        return "redirect:" + defaultTargetUrl;
	      }
	}

	@RequestMapping(value = "/index**", method = RequestMethod.GET)
	public String welcomePage() {
		return "index";
	}
	
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public String adminPage() {
		return "admin/admin";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, 
			HttpServletResponse response) {
	    Authentication auth = 
	    		SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login";
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

	  ModelAndView model = new ModelAndView();

	  Authentication auth = 
			  SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		model.addObject("username", (String)auth.getPrincipal());
	  }

	  model.setViewName("403");
	  return model;

	}

}
