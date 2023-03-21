package com.springmvc.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.boardService.boardService;
import com.springmvc.domain.boardDTO;

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
	@GetMapping("/view/{num}")	//게시글 보여주기
	public String view(@PathVariable("num") int num, Model model) {		
		model.addAttribute("board",bs.view(num));
		model.addAttribute("data","view테스트입니다.");
		return "view";
	}
	
	@GetMapping("/write")	//게시글 작성 페이지 매핑
	public String write(@ModelAttribute("board") boardDTO board, Model model) {		
		
		model.addAttribute("data","write테스트입니다.");
		return "write";
	}
	
	@PostMapping("/write")	//게시글 작성
	public String writeaction(@ModelAttribute("board") boardDTO board, Model model,HttpServletRequest req) {		
		bs.setboard(board, req);
		model.addAttribute("data","write테스트입니다.");
		model.addAttribute("msg",1);
		return "redirect:/";
	}
	
	@GetMapping("/update/{num}")	//게시글 수정 페이지 매핑
	public String update(@PathVariable("num")int num,Model model) {
		model.addAttribute("board",bs.view(num));
		model.addAttribute("data","update테스트입니다.");
		return "update";
	}
	@PostMapping("/update/{num}")	//게시글 수정
	public String updateaction(@PathVariable("num")int num, @ModelAttribute("board")boardDTO board, Model model,HttpServletRequest req) {
		bs.updateboard(board, req);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{num}")	//게시글 삭제
	public String delete(@PathVariable("num")int num) {
		bs.deleteboard(num);
		return "redirect:/";
	}
	
	
}
