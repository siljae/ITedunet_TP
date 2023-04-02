package com.springmvc.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springmvc.domain.boardDTO;
import com.springmvc.domain.criteria;

public interface BoardService {	
	//게시글 등록하기
	public void writeboard(boardDTO board,HttpServletRequest req);
	//게시판목록가져오기 기능
	public void getboardlist(Model model,criteria cri);	
	//게시판 정렬 기능
	public void getsortboardlist(Model model, criteria cri, String sort);
	//게시글 가져오기
	public void getboardview(Model model,  HttpServletRequest req);
	//게시글 수정하기
	public void updateboard(boardDTO board,HttpServletRequest req);	
	public boardDTO getByNum(int num,  HttpServletRequest req);
	public void deleteboard(String num);
	public void search(Model model, String content, criteria cri);
	//추천 기능
	public void recom(Model model, int pageNum, int num, String recom, HttpServletRequest req);
	//인기글 가져오기
	public void recomboard(Model model);
	
	
	/* public JSONArray sortboard(Model model,HttpServletRequest req); */
	
	
}
