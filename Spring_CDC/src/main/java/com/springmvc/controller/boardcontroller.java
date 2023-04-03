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
		br.getcommuboardlist(model, cri);
		return "commuboard";
	}
	
	@GetMapping("/commu/view/{pageNum}/{num}") //자랑해요 게시글 상세 페이지
	public String commuview(@PathVariable("pageNum") int pageNum,@PathVariable("num") int num, Model model, HttpServletRequest req) {
		model.addAttribute("num",num);
		model.addAttribute("pageNum",pageNum);
		br.getcommuboardview(model, req);
		return "commuboardview";
	}
	
	@GetMapping("/commu/view/{pageNum}/updateboard/{num}") //자랑해요 게시글 수정 페이지
	public ModelAndView updatecommuboardview(@PathVariable("pageNum") int pageNum,@PathVariable("num") int num,@ModelAttribute("updateboard") boardDTO board,Model model,HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		board = br.getcommuboardByNum(num , req);
		mav.addObject("updateboard",board);				
		mav.addObject("num",num);
		mav.addObject("pageNum",pageNum);
		mav.setViewName("updateboard");
		return mav;
	}
	
	@GetMapping("/qna/view/{pageNum}/updateboard/{num}") //Q&A 게시글 수정 페이지
	public ModelAndView updateqnaboardview(@PathVariable("pageNum") int pageNum,@PathVariable("num") int num,@ModelAttribute("updateboard") boardDTO board,Model model,HttpServletRequest req) {
		ModelAndView mav = new ModelAndView();
		board = br.getqnaboardByNum(num , req);
		mav.addObject("updateboard",board);				
		mav.addObject("num",num);
		mav.addObject("pageNum",pageNum);
		mav.setViewName("updateboard");
		return mav;
	}
	
	@PostMapping("/commu/view/{pageNum}/updateboard/{num}") //게시글 수정 기능
	public String updatecommuboard(@PathVariable("num") String num,@PathVariable("pageNum") String pageNum,@ModelAttribute("updateboard") boardDTO board,Model model,HttpServletRequest req) {
		br.updatecommuboard(board, req);
		return "redirect:/board/"+pageNum;
	}
	
	@PostMapping("/qna/view/{pageNum}/updateboard/{num}") //게시글 수정 기능
	public String updateqnaboard(@PathVariable("num") String num,@PathVariable("pageNum") String pageNum,@ModelAttribute("updateboard") boardDTO board,Model model,HttpServletRequest req) {
		br.updateqnaboard(board, req);
		return "redirect:/board/"+pageNum;
	}
	
	@GetMapping("/commu/view/{pageNum}/deleteboard/{num}")	//자랑해요 게시글 삭제
	public String deletecommuboard(@PathVariable("num") int num,@PathVariable("pageNum") String pageNum) {
		br.deletecommuboard(num);
		return "redirect:/board/"+pageNum;
	}
	
	@GetMapping("/qna/view/{pageNum}/deleteboard/{num}")	//Q&A 게시글 삭제
	public String deleteqnaboard(@PathVariable("num") int num,@PathVariable("pageNum") String pageNum) {
		br.deleteqnaboard(num);
		return "redirect:/board/"+pageNum;
	}
	
	@GetMapping("/commu/view/{pageNum}/{num}/{recom}")	//자랑해요 추천기능
	public String commurecom(@PathVariable("pageNum") int pageNum,@PathVariable("num") int num, @PathVariable("recom")String recom,Model model, HttpServletRequest req) {
		br.commurecom(model, pageNum, num, recom, req);
		return "commuboardview";
	}
	
	@GetMapping("/qna/view/{pageNum}/{num}/{recom}")	//Q&A 추천기능
	public String qnarecom(@PathVariable("pageNum") int pageNum,@PathVariable("num") int num, @PathVariable("recom")String recom,Model model, HttpServletRequest req) {
		br.qnarecom(model, pageNum, num, recom, req);
		return "qnaboardview";
	}
	
	@GetMapping("/commu/{sort}/1")	//자랑해요 게시판 정렬 기능
	public String commusort(@PathVariable("sort")String sort, Model model, criteria cri) {
		model.addAttribute("sort", sort);
		br.getsortcommuboardlist(model, cri, sort);
		return "commuboard";
	}
	
	@GetMapping("/commu/{sort}/{num}")	//정렬된 자랑해요 게시판 페이징처리
	public String commusortpage(@PathVariable("sort")String sort, @PathVariable("num")int num, Model model, criteria cri) {
		cri.setPagenum(num);
		model.addAttribute("sort",sort);
		br.getsortcommuboardlist(model, cri, sort);
		return "commuboard";
	}
	
	
	
	@GetMapping("/qna/{num}") //Q&A 게시판
	public String qnaboard(@PathVariable("num")int num, Model model, criteria cri) {
		cri.setPagenum(num);
		br.getqnaboardlist(model, cri);
		return "qnaboard";
	}
	
	@GetMapping("/qna/view/{pageNum}/{num}")	//Q&A 게시글 상세 페이지
	public String qnaview(@PathVariable("pageNum") int pageNum,@PathVariable("num") int num,Model model, HttpServletRequest req) {
		model.addAttribute("num",num);
		br.getqnaboardview(model, req);
		return "qnaboardview";
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
	
	@PostMapping("/search") //전체게시글 제목 or 내용 검색
	public String serach(Model model, HttpServletRequest req, criteria cri) {
		String content = req.getParameter("content");
		br.search(model, content, cri);
		return "board";
	}
	
	@PostMapping("/commu/search")	//자랑해요 게시글 제목 or 내용 검색
	public String commusearch(Model model, HttpServletRequest req, criteria cri) {
		String content = req.getParameter("content");
		br.commusearch(model, content, cri);
		return "commuboard";
	}
	
	@PostMapping("/qna/search")		//Q&A 게시글 제목 or 내용 검색
	public String qnasearch(Model model, HttpServletRequest req, criteria cri) {
		String content = req.getParameter("content");
		br.qnasearch(model, content, cri);
		return "qnaboard";
	}
	
	@GetMapping("/{search}/{pageNum}")	//전체게시판 검색 페이징처리
	public String boardsearch(@PathVariable("search") String search, @PathVariable("pageNum") int pageNum,Model model, criteria cri) {
		cri.setPagenum(pageNum);
		br.search(model, search, cri);
		return "board";
	}
	
	@GetMapping("/commu/search/{search}/{pageNum}")	//자랑해요 게시판 검색 페이징처리
	public String commusearchpage(@PathVariable("search") String search, @PathVariable("pageNum") int pageNum,Model model, criteria cri) {
		cri.setPagenum(pageNum);
		br.commusearch(model, search, cri);
		return "commuboard";
	}
	
	@GetMapping("/qna/search/{search}/{pageNum}")	//Q&A 게시판 검색 페이징처리
	public String qnasearchpage(@PathVariable("search") String search, @PathVariable("pageNum") int pageNum,Model model, criteria cri) {
		cri.setPagenum(pageNum);
		br.qnasearch(model, search, cri);
		return "qnaboard";
	}
	
	@GetMapping("/qna/{sort}/1")	//Q&A 정렬 기능
	public String qnasort(@PathVariable("sort")String sort, Model model, criteria cri) {
		model.addAttribute("sort",sort);
		br.getsortqnaboardlist(model, cri, sort);
		return "qnaboard";		
	}
	
	@GetMapping("/qna/{sort}/{num}")	//정렬된 Q&A 게시판 페이징 처리
	public String qnasortpage(@PathVariable("sort")String sort, @PathVariable("num")int num, Model model, criteria cri) {
		cri.setPagenum(num);
		model.addAttribute("sort",sort);
		br.getsortqnaboardlist(model, cri, sort);
		return "qnaboard";
	}
	
	@GetMapping("/recom") //추천해요 게시판
	public String recomboard(Model model, criteria cri) {
		br.getarecomboardlist(model, cri);
		return "recomboard";
	}
	
	@PostMapping("/recom/search")	//추천해요 검색 기능
	public String recomsearch(Model model, HttpServletRequest req, criteria cri) {
		System.out.println("추천컨트롤");
		String content = req.getParameter("content");
		br.recomsearch(model, content, cri);
		return "recomboard";
	}
	
	@GetMapping("/recom/search/{search}/{num}")	//추천해요 게시판 검색 페이징처리
	public String recomsearchpage(@PathVariable("search") String search, @PathVariable("pageNum") int pageNum,Model model, criteria cri) {
		cri.setPagenum(pageNum);
		br.recomsearch(model, search, cri);
		return "recomboard";
	}
}
