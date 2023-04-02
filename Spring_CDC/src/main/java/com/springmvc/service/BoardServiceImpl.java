package com.springmvc.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.springmvc.domain.boardDTO;
import com.springmvc.domain.criteria;
import com.springmvc.domain.fileDTO;
import com.springmvc.domain.pageDTO;
import com.springmvc.repository.BoardRepositoty;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepositoty br;

	@Override //글쓰기
	public void writeboard(boardDTO board,HttpServletRequest req) {		
		br.writeboard(board,req);
	}

	@Override	//게시판목록기능
	public void getboardlist(Model model,criteria cri) {
		int total = br.getallcount();
		pageDTO page = new pageDTO(cri, total);
		List<boardDTO> boardlist = br.getboardlist(page);
		List<boardDTO> bestrecomlist = br.getrecomboard();
		model.addAttribute("boardlist",boardlist);
		model.addAttribute("bestrecomlist",bestrecomlist);
		model.addAttribute("page",page);
	}
	
	@Override //게시판 정렬(최신순(기본), 인기순, 조회순)
	public void getsortboardlist(Model model, criteria cri, String sort) {
		int total = br.getallcount();
		pageDTO page = new pageDTO(cri, total);
		List<boardDTO> boardlist = br.getsortboardlist(page, sort);
		model.addAttribute("boardlist",boardlist);
		model.addAttribute("page",page);
	}

	@Override //선택된 게시글 상세 페이지 가져오기
	public void getboardview(Model model,  HttpServletRequest req) {
		int num =  (int)model.getAttribute("num");
		boardDTO board = br.getboardview(num, req);
		br.updatehit(num);
		model.addAttribute("board",board);	
	}

	@Override	//게시글수정
	public void updateboard(boardDTO board, HttpServletRequest req) {
		boardDTO fileboard = br.getboardview(board.getNum(), req);
		board.setFiles(fileboard.getFiles());
		br.updateboard(board, req);
	}

	@Override	//게시글번호가져오기
	public boardDTO getByNum(int num,  HttpServletRequest req) {
		boardDTO board = br.getboardview(num, req);
		if(board.getBoard_type().equals("자랑해요")) {
			board.setBoard_type("commu");
		}
		if(board.getTagvalue().equals("고양이")) {
			board.setAnimal_type("cat");
		}
		if(board.getTagvalue().equals("강아지")) {
			board.setAnimal_type("dog");
		}
		return board;
	}

	@Override	//게시글 삭제
	public void deleteboard(String num) {		
		br.deleteboard(num);
		
	}
	
	@Override	//게시글 검색
	public void search(Model model, String content, criteria cri) {
		int total = br.getcount(content);
		pageDTO page = new pageDTO(cri, total);
		List<boardDTO> boardlist = br.search(content, page);
		model.addAttribute("search",content);
		model.addAttribute("boardlist",boardlist);
		model.addAttribute("page",page);
	}

	@Override	//추천 기능
	public void recom(Model model, int pageNum, int num, String recom, HttpServletRequest req) {
		br.recom(num, recom);
		boardDTO board = br.getboardview(num, req);
		model.addAttribute("num",num);
		model.addAttribute("pageNum",pageNum);
		model.addAttribute("board",board);
		
	}

	@Override	//인기글 가져오기
	public void recomboard(Model model) {
		List<boardDTO> recomlist = br.getrecomboard();
		model.addAttribute("recomlist",recomlist);		
	}

	/*
	 * @Override //만들었으나 아직 활용을 안함 public JSONArray sortboard(Model model,
	 * HttpServletRequest req) { br.boardlist(model, req); List<boardDTO> boardlist
	 * = (List<boardDTO>) model.getAttribute("boardlist"); JSONArray jboardlist =
	 * new JSONArray(); for(boardDTO board : boardlist) { JSONObject jboard = new
	 * JSONObject(); jboard.put("num", board.getNum()); jboard.put("name",
	 * board.getName()); jboard.put("board_type", board.getBoard_type());
	 * jboard.put("animal_type", board.getAnimal_type()); jboard.put("title",
	 * board.getTitle()); jboard.put("content", board.getContent());
	 * jboard.put("regist_day", board.getRegist_day()); jboard.put("hit",
	 * board.getHit()); jboard.put("filename", board.getFilename());
	 * jboard.put("tag_src", board.getTag_src()); jboard.put("tag_value",
	 * board.getTag_value()); jboard.put("tag_calregist", board.getCalregist());
	 * jboard.put("tag_recom", board.getRecom()); jboardlist.put(jboard); } return
	 * jboardlist; }
	 */
	
	
	
}
