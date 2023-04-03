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
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.boardDTO;
import com.springmvc.domain.criteria;
import com.springmvc.service.BoardService;

@Controller
@RequestMapping("/board")
public class boardcontroller {
	
	@Autowired
	BoardService br;

	@RequestMapping("/") //전체 게시판
	public String board(Model model,HttpServletRequest req, criteria cri) {
		br.getallboardlist(model, cri);
		return "board/";
	}
	
	@GetMapping("/{num}")	//페이징된 전체 게시판
	public String boardnum(@PathVariable("num")int num, Model model,HttpServletRequest req, criteria cri) {
		cri.setPagenum(num);
		br.getallboardlist(model, cri);
		return "board";
	}
	
	@GetMapping("/commu/{num}") //커뮤니티 게시판 
	public String commuboard(@PathVariable("num")int num,Model model, criteria cri) {
		cri.setPagenum(num);
		br.getallboardlist(model, cri);
		return "commuboard";
	}
	
	@GetMapping("/commu/view/{pageNum}/{num}") //게시글 상세 페이지
	public String commuview(@PathVariable("pageNum") int pageNum,@PathVariable("num") int num,Model model, HttpServletRequest req) {
		model.addAttribute("num",num);
		model.addAttribute("pageNum",pageNum);
		br.getboardview(model, req);
		return "commuboardview";
	}
	
	@GetMapping("/commu/view/{pageNum}/updateboard/{num}") //게시글 수정 페이지
	public ModelAndView updateboardview(@PathVariable("pageNum") int pageNum,@PathVariable("num") int num,@ModelAttribute("updateboard") boardDTO board,Model model,HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		board = br.getByNum(num , req);
		mav.addObject("updateboard",board);				
		mav.addObject("num",num);
		mav.addObject("pageNum",pageNum);
		mav.setViewName("updateboard");		
		return mav;
	}
	
	@PostMapping("/commu/view/{pageNum}/updateboard/{num}") //게시글 수정 기능
	public String updateboard(@PathVariable("num") String num,@PathVariable("pageNum") String pageNum,@ModelAttribute("updateboard") boardDTO board,Model model,HttpServletRequest req) {
		br.updateboard(board, req);
		return "redirect:/board/"+pageNum;
	}
	
	@GetMapping("/commu/view/{pageNum}/deleteboard/{num}")	//게시글삭제
	public String deleteboard(@PathVariable("num") String num,@PathVariable("pageNum") String pageNum) {
		br.deleteboard(num);
		return "redirect:/board/"+pageNum;
	}
	
	@GetMapping("/commu/view/{pageNum}/{num}/{recom}")	//추천기능
	public String viewrecom(@PathVariable("pageNum") int pageNum,@PathVariable("num") int num, @PathVariable("recom")String recom,Model model, HttpServletRequest req) {
		br.recom(model, pageNum, num, recom, req);
		return "commuboardview";
	}
	
	@GetMapping("/commu/{sort}/1")	//정렬 기능
	public String viewed(@PathVariable("sort")String sort, Model model, criteria cri) {
		model.addAttribute("sort", sort);
		br.getsortboardlist(model, cri, sort);
		return "commuboard";
	}
	
	@GetMapping("/commu/{sort}/{num}")	//정렬된 게시판 페이징처리
	public String urlsort(@PathVariable("sort")String sort, @PathVariable("num")int num, Model model, criteria cri) {
		cri.setPagenum(num);
		model.addAttribute("sort",sort);
		br.getsortboardlist(model, cri, sort);
		return "commuboard";
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
	
	@GetMapping("/boardwrite")	//게시글 작성 페이지
	public String boardwrite(@ModelAttribute("board") boardDTO board,Model model) {
		return "boardwrite";
	}
	
	@PostMapping("/boardwrite")	//게시글 등록
	public String wrtie(@ModelAttribute("board") boardDTO board,Model model,HttpServletRequest req) {
		br.writeboard(board,req);
		
		return "redirect:/board/1";
	}
	
	@PostMapping("/search") //게시글 제목 or 내용 검색
	public String serach(Model model, HttpServletRequest req, criteria cri) {
		String content = req.getParameter("content");
		br.search(model, content, cri);
		return "board";
	}
	
	@GetMapping("/{search}/{pageNum}")	//검색된 게시글 페이징처리
	public String urlsearch(@PathVariable("search") String search, @PathVariable("pageNum") int pageNum,Model model, criteria cri) {
		cri.setPagenum(pageNum);
		br.search(model, search, cri);
		return "board";
	}
	

	
}
