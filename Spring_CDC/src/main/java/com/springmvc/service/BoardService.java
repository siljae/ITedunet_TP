package com.springmvc.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springmvc.domain.boardDTO;
import com.springmvc.domain.criteria;

public interface BoardService {
	public void writeboard(boardDTO board,HttpServletRequest req);
	public void boardlist(Model model,HttpServletRequest req, criteria cri);
	public void requestboardview(Model model);
	public void updateboard(boardDTO board,HttpServletRequest req);
	public boardDTO getByNum(int num);
	public void deleteboard(String num);
	public void search(Model model, String content, criteria cri);
	public void recom(Model model, String pageNum, String num, String recom);
	public void recomboard(Model model);
	
	
	/* public JSONArray sortboard(Model model,HttpServletRequest req); */
	
	
}
