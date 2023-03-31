package com.springmvc.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springmvc.domain.boardDTO;
import com.springmvc.domain.criteria;

public interface BoardService {	
	public void writeboard(boardDTO board,HttpServletRequest req);
	//게시판목록가져오기 기능
	public void getboardlist(Model model,criteria cri);
	public void boardlist(Model model,HttpServletRequest req, criteria cri);
	public void getboardview(Model model);
	public void updateboard(boardDTO board,HttpServletRequest req);
	public boardDTO getByNum(int num);
	public void deleteboard(String num);
	public void search(Model model, String content, criteria cri);
	public void recom(Model model, String pageNum, String num, String recom);
	public void recomboard(Model model);
	
	
	/* public JSONArray sortboard(Model model,HttpServletRequest req); */
	
	
}
