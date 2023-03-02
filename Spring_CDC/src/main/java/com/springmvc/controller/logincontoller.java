package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.domain.memberDTO;

@Controller
@RequestMapping("/login")
public class logincontoller {
	
	@RequestMapping
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup(@ModelAttribute("member") memberDTO member) {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String join(@ModelAttribute("member") memberDTO member) {
		return "signup2";
	}
	
	@InitBinder
	public void initBinde(WebDataBinder binder) {
		binder.setAllowedFields("email","name","pw","phone1","phone2","phone3","post","addr1","addr2");
	}
}
