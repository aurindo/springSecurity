package com.springcookbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springcookbook.config.DatasourceConfig;

@Controller
public class HelloController {
	
	@Autowired
	private DatasourceConfig datasourceConfig;
	
	@RequestMapping("hi")
	@ResponseBody
	public String hi() {
		return "Hello, world.";
	}
}