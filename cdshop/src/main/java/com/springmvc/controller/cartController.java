package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.domain.cartDTO;
import com.springmvc.service.cartService;
import com.springmvc.service.productService;


@Controller
@RequestMapping(value="/cart")
public class cartController {
	@Autowired
	cartService cs;
	@Autowired
	private productService ps;
	
	@GetMapping
	public String requestCartId(HttpServletRequest request) {
		// 웹요청 /cart일때 요청처리 메서드
		System.out.println("cartcontroller- 매핑들어오니? :");
		String sessionid = request.getSession(true).getId();
		// 세션 id 값 가져옴
		System.out.println("cartcontroller- 세션 id 가지고 왔니? : " + sessionid);
		return "redirect:/cart/" + sessionid;
		// 세션 id 값 uri cart/sessionid로 리다이렉션함
	}
	
	
	
}
