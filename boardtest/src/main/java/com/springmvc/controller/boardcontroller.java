package com.springmvc.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.boardService.boardService;

@Controller
@RequestMapping("/")
public class boardcontroller {
	
	@Autowired
	private boardService bs;
	
	@RequestMapping	//메인페이지, 게시글 목록페이지
	public String list(Model model) {
		model.addAttribute("boardlist",bs.getboardlist());		
		return "list";
	}
	
	@GetMapping("/view")	//게시글 보여주기
	public String view(Model model) {
		model.addAttribute("data","view테스트입니다.");
		return "view";
	}
	
	@GetMapping("/write")	//게시글 작성
	public String write(Model model) {
		model.addAttribute("data","write테스트입니다.");
		return "write";
	}
	
	@GetMapping("/update")	//게시글 수정
	public String update(Model model) {
		model.addAttribute("data","update테스트입니다.");
		return "update";
	}
	
	
}
