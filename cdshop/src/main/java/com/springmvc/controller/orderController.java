//package com.springmvc.controller;
//
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.springmvc.domain.cartDTO;
//import com.springmvc.domain.orderDTO;
//import com.springmvc.service.orderService;
//
//@Controller
//@RequestMapping(value="/order")
//public class orderController {
//	@Autowired
//	private orderService os;
//	
//	@RequestMapping
//	public String requestOrder(@ModelAttribute orderDTO order, HttpSession session) {
//		String name = (String) session.getAttribute("name");
//		order.setName(name);
//		return "redirect:";
//	}
//	
//	
//}
