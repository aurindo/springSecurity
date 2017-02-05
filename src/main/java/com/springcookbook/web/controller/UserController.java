package com.springcookbook.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springcookbook.model.User;
import com.springcookbook.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public User defaultUser() {
		User user = new User();
		user.setEnable(false);
		return user;
	}
	
	@ModelAttribute("roles")
	public Map<String, String>countries() {
	  Map<String, String> roles = new HashMap<String, String>();
	  
	  userService.findAllRoles().stream().forEach(role -> {
		  roles.put(role.getName(), role.getName());
	  });

	  return roles;
	}

	@RequestMapping("list")
	public void userList(Model model) {
		model.addAttribute("listUsers", userService.findAllUsers());
	}

	@RequestMapping("add")
	public void addUser() {
		System.out.println("Call the method addUser()!");
	}

	@RequestMapping("delete/{id}")
	public String  deleteUser(@PathVariable Long id) {
		userService.delete(id);
		return "redirect:/user/list";
	}
	
	@RequestMapping("edit/{id}")
	public ModelAndView editUser(
			@ModelAttribute("user") User user, 
			@PathVariable Long id) {
		
		user = userService.findUserById(id);
		ModelAndView model = new ModelAndView("user/edit", "user", user);
		
		return model;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") @Valid User user,
			BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			return "/user/add";
		} else {
			userService.addUser(user);
			return "redirect:/user/list";
		}

	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("user") @Valid User user,
			BindingResult bindingResult) throws Exception {

		if (bindingResult.hasErrors()) {
			return "/user/edit/" + user.getId();
		} else {
			userService.editUser(user);
			return "redirect:/user/list";
		}

	}
	
}
