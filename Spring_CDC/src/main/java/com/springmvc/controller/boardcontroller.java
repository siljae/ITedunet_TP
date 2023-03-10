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
import org.springframework.web.servlet.ModelAndView;

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
	
	@GetMapping("/commu/view/{num}/{pageNum}") //게시글 상세 페이지
	public String commuview(@PathVariable String num,@PathVariable String pageNum,Model model) {
		model.addAttribute("num",num);
		model.addAttribute("pageNum",pageNum);
		br.requestboardview(model);
		return "commuboardview";
	}
	
	@GetMapping("/commu/view/{num}/updateboard/{pageNum}") //게시글 수정 페이지
	public ModelAndView updateboardview(@PathVariable String num,@PathVariable String pageNum,@ModelAttribute("updateboard") boardDTO board,Model model,HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		int numInt = Integer.parseInt(num);
		board = br.getByNum(numInt);
		mav.addObject("updateboard",board);				
		mav.addObject("num",num);
		mav.addObject("pageNum",pageNum);
		mav.setViewName("updateboard");
		
		return mav;
	}
	
	@PostMapping("/commu/view/{num}/updateboard/{pageNum}") //게시글 수정 기능
	public String updateboard(@PathVariable String num,@PathVariable String pageNum,@ModelAttribute("updateboard") boardDTO board,Model model,HttpServletRequest req) {
		model.addAttribute("num",num);
		model.addAttribute("pageNum",pageNum);
		br.updateboard(board, req);		
		return "redirect:/board";
	}
	
	@GetMapping("/commu/view/{num}/deleteboard/{pageNum}")
	public String deleteboard(@PathVariable String num,@PathVariable String pageNum) {
		br.deleteboard(num);
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
		br.writeboard(board,req);
		
		return "board";
	}
	
	@GetMapping("/pagenum")
	public String movepage(@PathVariable("pagenum") String pagenum,Model model) {
		model.addAttribute("pagenum",pagenum);
		return "";
	}
}
