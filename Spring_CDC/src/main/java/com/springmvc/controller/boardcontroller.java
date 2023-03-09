package com.springmvc.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.springmvc.domain.boardDTO;
import com.springmvc.service.BoardService;

@Controller
@RequestMapping("/board")
public class boardcontroller {
	
	@Autowired
	BoardService br;

	@RequestMapping //전체 게시판
	public String board(Model model,HttpServletRequest req) {
		br.boardlist(model,req);
		return "board";
	}
	
	@GetMapping("/commu") //커뮤니티 게시판 
	public String commuboard(Model model,HttpServletRequest req) {
		br.boardlist(model,req);
		return "commuboard";
	}
	
	@GetMapping("/commu/view/{num}/{pageNum}")
	public String commuview(@PathVariable String num,@PathVariable String pageNum,Model model) {
		model.addAttribute("num",num);
		model.addAttribute("pageNum",pageNum);
		br.requestboardview(model);
		return "commuboardview";
	}
	
	@GetMapping("/commu/view/{num}/updateboard/{pageNum}")
	public String updateboardview(@PathVariable String num,@PathVariable String pageNum,@ModelAttribute("updateboard") boardDTO board,Model model,HttpServletRequest req) {
		model.addAttribute("num",num);
		model.addAttribute("pageNum",pageNum);		
		return "updateboard";
	}
	
	@PostMapping("/updateboard/{num}/{pageNum}")
	public String updateboard(@PathVariable String num,@PathVariable String pageNum,@ModelAttribute("updateboard") boardDTO board,Model model,HttpServletRequest req) {
		model.addAttribute("num",num);
		model.addAttribute("pageNum",pageNum);
		br.updateboard(board, req);
		return "board";
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
	
	@GetMapping("/boardwrite")
	public String boardwrite(@ModelAttribute("board") boardDTO board,Model model) {
		return "boardwrite";
	}
	
	@PostMapping("/boardwrite")
	public String wrtie(@ModelAttribute("board") boardDTO board,Model model,HttpServletRequest req) {		
		System.out.println(board.toString());
		System.out.println("보드컨트롤러: "+board.getFilename());
		br.writeboard(board,req);
		
		return "board";
	}
	
	@GetMapping("/pagenum")
	public String movepage(@PathVariable("pagenum") String pagenum,Model model) {
		model.addAttribute("pagenum",pagenum);
		return "";
	}
}
