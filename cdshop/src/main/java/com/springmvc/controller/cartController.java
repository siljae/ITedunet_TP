package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.domain.cart;
import com.springmvc.service.cartService;

@Controller
@RequestMapping(value="/cart")
public class cartController {
	@Autowired
	private cartService cartservice;
	
	@GetMapping
	public String requestCartId(HttpServletRequest request) {
		String sessionid = request.getSession(true).getId();
		return "redirect:/cart" + sessionid;
	}
	
	@PostMapping
	public @ResponseBody cart create(@RequestBody cart Cart) {
		return cartservice.create(Cart);
	}
	
	@GetMapping("/{cartId}")
	public String requestCartList(@PathVariable(value="cartId") String cartId, Model model) {
		cart Cart = cartservice.read(cartId);
		model.addAttribute("Cart", Cart);
		return "mypagecart";
	}
	
	@PutMapping("/{cartId}")
	public @ResponseBody cart read(@PathVariable(value="cartId")String cartId) {
		return cartservice.read(cartId);
	}
}
