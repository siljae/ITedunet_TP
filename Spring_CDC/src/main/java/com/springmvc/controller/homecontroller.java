package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
 public class homecontroller {  

	@RequestMapping
	public String index() {
		return "index";
	}
	@GetMapping("/home")
	public String home() {
		return "index";
	}
	
}