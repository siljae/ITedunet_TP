package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shopmain")
public class productcontroller {
	
	@RequestMapping //main 화면
	public String shopmain() {
		return "shopslide";
	}
	
	@GetMapping("/productlist")//카테고리안 페이지 눌렀을때 화면
	public String productlist() {
		return "productlist";
	}
	
	@GetMapping("/productview")//상품상세
	public String productview() {
		return "productpage";
	}
	
	@GetMapping("/productadd")//상품추가
	public String productadd() {
		return "productadd";
	}
	@GetMapping("/dogfood")
	public String p_dogfood() {
		return "p_dogfood";
	}
	
	@GetMapping("/dogsnack")
	public String p_dogsnack() {
		return "p_dogsnack";
	}
	
	@GetMapping("/dogsup")
	public String p_dogsup() {
		return "p_dogsup";
	}
	
	@GetMapping("/dogtoy")
	public String p_dogtoy() {
		return "p_dogtoy";
	}
	
	@GetMapping("/catfood")
	public String p_catfood() {
		return "p_catfood";
	}
	
	@GetMapping("/catsnack")
	public String p_catsnack() {
		return "p_catsnack";
	}
	
	@GetMapping("/catsup")
	public String p_catsup() {
		return "p_catsup";
	}
	
	@GetMapping("/cattoy")
	public String p_cattoy() {
		return "p_cattoy";
	}
}
