package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class mypagecontroller {

	@RequestMapping //마이페이지 이동
	public String mypage() {
		return "mypage";
	}
	
	@GetMapping("/cart") //마이페이지의 장바구니 이동
	public String cart() {
		System.out.println("mypage cart로 들어오나?");
		return "mypagecart";
	}
	
	@GetMapping("/chat") //마이페이지의 채팅 이동
	public String chat() {
		return "mypagechat";
	}
	
	@GetMapping("/chatting") //1:1 채팅 팝업창 띄우기
	public String chatpop() {
		return "chat";
	}
	
	@GetMapping("/order") //마이페이지의 주문목록 이동
	public String order() {
		return "mypageorder";
	}
	
	@GetMapping("/barrier") //마이페이지의 개인정보수정
	public String veri() {
		return "mypagebarrier";
	}
}