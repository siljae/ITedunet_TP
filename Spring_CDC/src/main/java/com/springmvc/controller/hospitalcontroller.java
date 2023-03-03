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
	
	@GetMapping("/reviews")
	public String hospitalreviews() {
		return "hospitalreviews";
	}
	
	@GetMapping("/reviews/view")
	public String hospitalview() {
		return "hospitalview";
	}
}
