package com.springcookbook.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springcookbook.dao.UserDAO;
import com.springcookbook.model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@ModelAttribute("user")
	public User defaultUser() {
		User user = new User();
		return user;
	}

	@RequestMapping("/list")
	public void userList(Model model) {
		List<User> userSize = userDAO.findAll();
		model.addAttribute("nbUsers", userSize.size());
		System.out.println("Call the method userList()!");
	}

	@RequestMapping("/add")
	public void addUser() {
		System.out.println("Call the method addUser()!");
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUserSubmit(@ModelAttribute("user") @Valid User user,
			BindingResult bindingResult) {
		System.out.println("Call the method addUserSubmit()!");

		if (bindingResult.hasErrors()) {
			// show the form page again, with the errors
			return "/user/add";
		} else {
			userDAO.add(user);
			return "redirect:/user/list";
		}

	}
}
