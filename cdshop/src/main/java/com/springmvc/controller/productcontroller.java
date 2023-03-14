package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.productDTO;
import com.springmvc.service.productService;

@Controller
@RequestMapping("/shopmain")
public class productcontroller {
	@Autowired
	productService ps;
	
//	@RequestMapping //main 화면
//	public String shopmain(Model model) {
//		List<productDTO> list = ps.getAllProductList();
//		model.addAttribute("productlist", list);
//		return "shopslide";
//	}
	
	@GetMapping("/all")//카테고리안 페이지 눌렀을때 화면
	public ModelAndView productlist(Model model) {
		ModelAndView modelandview = new ModelAndView();
		List<productDTO> list = ps.getAllProductList();
		for(productDTO dto : list) {
			System.out.println(dto.getProductId());
		}
		modelandview.addObject("productlist", list);
		modelandview.setViewName("shopslide");
		return modelandview;
	}
	
	@GetMapping("/productview")//상품상세
	public String productview(@RequestParam("id") String productId, Model model) {
		System.out.println("너 productview 들어옴?");
		productDTO productById = ps.getProductById(productId);
		System.out.println(productById);
		model.addAttribute("product", productById);
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
