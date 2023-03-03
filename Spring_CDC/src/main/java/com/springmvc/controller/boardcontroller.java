package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class boardcontroller {

	@RequestMapping //전체 게시판
	public String board() { 
		return "board";
	}
	
	@GetMapping("/commu") //커뮤니티 게시판 
	public String commuboard() {
		return "commuboard";
	}
	
	@GetMapping("/commu/view")
	public String commuview() {
		return "commuboardview";
	}
	
	@GetMapping("/qna") //묻고답하기 게시판
	public String qnaboard() {
		return "qnaboard";
	}
	
	@GetMapping("/qna/view")
	public String qnaview() {
		return "qnaboardview";
	}
	
	@GetMapping("/recom") //추천해요 게시판
	public String recomboard() {
		return "recomboard";
	}
	
	@GetMapping("/recom/view")
	public String reconview() {
		return "recomboardview";
	}
}
