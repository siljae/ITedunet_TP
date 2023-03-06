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
	
}
