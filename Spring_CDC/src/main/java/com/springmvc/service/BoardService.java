package com.springmvc.service;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.springframework.ui.Model;

import com.springmvc.domain.boardDTO;

public interface BoardService {
	public void writeboard(boardDTO board,HttpServletRequest req);
	public void boardlist(Model model,HttpServletRequest req);
	public void requestboardview(Model model);
	public void updateboard(boardDTO board,HttpServletRequest req);
	public boardDTO getByNum(int num);
	public void deleteboard(String num);
	public void search(Model model,HttpServletRequest req);
	public void recom(Model model, String pageNum, String num, String recom);
	public void recomboard(Model model);
	
	
	/* public JSONArray sortboard(Model model,HttpServletRequest req); */
	
	
}
