package com.springmvc.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.boardDTO;
import com.springmvc.service.BoardService;

@Controller
@RequestMapping("/board")
public class boardcontroller {
	
	@Autowired
	BoardService br;

	@RequestMapping("/") //전체 게시판
	public String board(Model model,HttpServletRequest req) {
		br.boardlist(model,req);
		return "board";
	}
	
	@GetMapping("/{pageNum}")
	public String boardnum(@PathVariable("pageNum") String pageNum, Model model,HttpServletRequest req) {
		req.setAttribute("pageNum", pageNum);
		br.boardlist(model,req);
		return "board";
	}
	
	@GetMapping("/commu/{pageNum}") //커뮤니티 게시판 
	public String commuboard(@PathVariable("pageNum") String pageNum, Model model,HttpServletRequest req) {
		req.setAttribute("pageNum", pageNum);
		br.boardlist(model,req);
		return "commuboard";
	}
	
	@GetMapping("/commu/view/{pageNum}/{num}") //게시글 상세 페이지
	public String commuview(@PathVariable("pageNum") String pageNum,@PathVariable("num") String num,Model model) {
		model.addAttribute("num",num);
		model.addAttribute("pageNum",pageNum);
		br.requestboardview(model);
		return "commuboardview";
	}
	
	@GetMapping("/commu/view/{pageNum}/updateboard/{num}") //게시글 수정 페이지
	public ModelAndView updateboardview(@PathVariable("pageNum") String pageNum,@PathVariable("num") String num,@ModelAttribute("updateboard") boardDTO board,Model model,HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		int numInt = Integer.parseInt(num);
		board = br.getByNum(numInt);
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
	
	@GetMapping("/commu/view/{pageNum}/{num}/{recom}")
	public String viewrecom(@PathVariable("pageNum") int pageNum,@PathVariable("num") int num, @PathVariable("recom")int recom) {
		
		return "redirect:";
	}
	
	@GetMapping("/commu/{pageNum}/{sort}")	//정렬 기능
	public String viewed(@PathVariable("pageNum")String pageNum, @PathVariable("sort")String sort, Model model,HttpServletRequest req) {
		System.out.println("컨트롤의 sort: "+sort);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("sort", sort);		
		br.boardlist(model, req);
		model.addAttribute("sort",sort);
		String a = (String)model.getAttribute("sort");
		System.out.println("a: "+a);
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
	
	@GetMapping("/boardwrite")
	public String boardwrite(@ModelAttribute("board") boardDTO board,Model model) {
		return "boardwrite";
	}
	
	@PostMapping("/boardwrite")
	public String wrtie(@ModelAttribute("board") boardDTO board,Model model,HttpServletRequest req) {
		br.writeboard(board,req);
		
		return "redirect:/board/1";
	}
	
	@GetMapping("/pagenum")
	public String movepage(@PathVariable("pagenum") String pagenum,Model model) {
		model.addAttribute("pagenum",pagenum);
		return "";
	}
	
	@PostMapping("/search") //게시글 제목 or 내용 검색
	public String serach(Model model,HttpServletRequest req) {
		br.search(model, req);
		return "board";
	}
	
}
