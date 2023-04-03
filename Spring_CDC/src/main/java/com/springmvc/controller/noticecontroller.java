package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.domain.boardDTO;

@Controller
@RequestMapping("/notice")
public class noticecontroller {
	
	

	@RequestMapping //공지사항 게시판
	public String notice() { 
		return "notice";
	}
	
	@GetMapping("/view")
	public String noticeview() {
		return "noticeview";
	}
	
	@GetMapping("/boardwrite")	//게시글 작성 페이지
	public String boardwrite(@ModelAttribute("board") boardDTO board,Model model) {
		return "boardwrite";
	}
	
	@PostMapping("/boardwrite")	//게시글 등록
	public String wrtie(@ModelAttribute("board") boardDTO board,Model model,HttpServletRequest req) {
		
		return "redirect:/board/1";
	}
	
	@GetMapping("/event") //이벤트 게시판
	public String event() {
		return "event";
	}
	
	@GetMapping("/event/view")
	public String eventview() {
		return "eventview";
	}
}
