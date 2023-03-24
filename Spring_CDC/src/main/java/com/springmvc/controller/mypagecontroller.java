package com.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvc.domain.memberDTO;
import com.springmvc.service.MemberService;

@Controller
@RequestMapping("/mypage")
public class mypagecontroller {
	
	@Autowired
	MemberService mr;

	@RequestMapping //마이페이지 이동
	public String mypage(@ModelAttribute("member")memberDTO member, Model model,HttpSession session) {
		member.setName((String) session.getAttribute("name"));
		mr.getmemberByname(member, model);
		return "mypagebarrier";
	}
	
	@GetMapping("/barrier") //마이페이지의 개인정보수정
	public String veri(@ModelAttribute("member")memberDTO member, Model model,HttpSession session) {
		member.setName((String) session.getAttribute("name"));
		mr.getmemberByname(member, model);
		return "mypagebarrier";
	}
	
	@PostMapping("/barrier")	//개인정보수정 전 한번 더 비밀번호를 입력받아 사용자가 맞는지 확인하는 기능
	public String chkuser(@ModelAttribute("member")memberDTO member, @RequestParam("chkpw") String chkpw, Model model,HttpSession session) {
		member.setName((String)session.getAttribute("name"));
		mr.getmemberByname(member, model);
		String page = mr.chkuser(member,model,chkpw);
		return page;
	}
	
	@GetMapping("/cart") //마이페이지의 장바구니 이동
	public String cart() {
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
	
	
	
	@PostMapping("/mypage")	//마이페이지 수정
	public String updatemypge(@ModelAttribute("member")memberDTO member) {
		mr.updatemember(member);
		return "redirect:/mypage";
	}
}
