package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping("/event") //이벤트 게시판
	public String event() {
		return "event";
	}
	
	@GetMapping("/event/view")
	public String eventview() {
		return "eventview";
	}
}
