package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hospital")
public class hospitalcontroller {

	@RequestMapping
	public String hospital() {
		return "hospitalmap";
	}
	
	@GetMapping("/review")
	public String hospitalreview() {
		return "hospitalreview";
	}
}
