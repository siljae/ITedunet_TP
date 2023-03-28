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

	@Override //게시판 목록
	public void boardlist(Model model,HttpServletRequest req, criteria cri) {
		br.boardlist(model,req);
		int total = br.getallcount();
		int pagenum = (int) model.getAttribute("pageNum");
		cri.setPagenum(pagenum);
		pageDTO page = new pageDTO(cri, total);
		model.addAttribute("page",page);
		
	}

	@Override //선택된 게시글 상세 페이지 가져오기
	public void requestboardview(Model model) {
		br.requestboardview(model);
		
	}

	@Override	//게시글수정
	public void updateboard(boardDTO board, HttpServletRequest req) {
		br.updateboard(board, req);
		
	}

	@Override	//게시글번호가져오기
	public boardDTO getByNum(int num) {
		boardDTO board = br.getByNum(num);		
		return board;
	}

	@Override	//게시글 삭제
	public void deleteboard(String num) {
		br.deleteboard(num);
		
	}
	
	@Override	//게시글 검색
	public void search(Model model, String content, criteria cri) {
		List<boardDTO> boardlist = br.search(content);
		int total = br.getcount(content);
		pageDTO page = new pageDTO(cri, total);
		model.addAttribute("boardlist",boardlist);
		model.addAttribute("page",page);
	}

	@Override	//추천 기능
	public void recom(Model model, String pageNum, String num, String recom) {
		br.recom(num, recom);
		model.addAttribute("num",num);
		model.addAttribute("pageNum",pageNum);
		br.requestboardview(model);
		
	}

	@Override	//인기글 가져오기
	public void recomboard(Model model) {
		List<boardDTO> recomlist = br.recomboard();
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
