package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.domain.memberDTO;
import com.springmvc.service.MemberService;

@Controller
@RequestMapping("/login")
public class logincontoller {
	@Autowired
	private MemberService mr;
	
	@RequestMapping
	public String login(){
		return "login";
	}
	
	@GetMapping("/pwsearch")
	public String pwsearch() {
		return "pw_search";
	}
	
	@GetMapping("/signup")
	public String signupForm(@ModelAttribute("member")memberDTO member) {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String submitsignup(@ModelAttribute("member") memberDTO member) throws Exception {
		mr.join(member);
		return "signup";
	}
	
	@PostMapping("/chklogin")
	public String submitlogin(HttpServletRequest req, Model model, HttpSession session){
		System.out.println("여기로 들어왔나");
		String[] result = mr.login(req.getParameter("email"),req.getParameter("pw"));
		
		model.addAttribute("result",result);
		return "index";
	}
	
	@GetMapping("/chkemail") //이메일 중복체크 //배리에이블 써야됨
	public String chkemail(HttpServletRequest req, Model model) {
		System.out.println("req"+req.getParameter("email"));
		model.addAttribute("email",req.getParameter("email"));
		return "check_email";
	}
	
	@PostMapping("/chkemail") //이메일 중복체크하고 와서 값 넘겨주기
	public String chkemail2(HttpServletRequest req) {
		return "check_email";
	}
	
	@GetMapping("/chkname") //닉네임 중복체크 //배리에이블 써야됨
	public String chkname(HttpServletRequest req, Model model) {
		model.addAttribute("name",req.getParameter("name"));
		return "check_name";
	}
	
	@PostMapping("/chkname") //닉네임 중복체크하고 와서 값 넘겨주기
	public String chkname2(HttpServletRequest req) {
		return "check_name";
	}
	
	@GetMapping("/mypage") //마이페이지 이동
	public String mypage() {
		return "mypage";
	}
	
	@GetMapping("/mypage/cart") //마이페이지의 장바구니 이동
	public String cart() {
		return "mypagecart";
	}
	
	@GetMapping("/mypage/chat") //마이페이지의 채팅 이동
	public String chat() {
		return "mypagechat";
	}
	
	@GetMapping("/chat") //1:1 채팅 팝업창 띄우기
	public String chatpop() {
		return "chat";
	}
	
	@GetMapping("/mypage/order") //마이페이지의 주문목록 이동
	public String order() {
		return "mypageorder";
	}
	
	@GetMapping("/mypage/barrier") //마이페이지의 개인정보수정
	public String veri() {
		return "mypagebarrier";
	}
	
}
