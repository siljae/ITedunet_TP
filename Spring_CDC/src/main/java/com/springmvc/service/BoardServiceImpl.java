package com.springmvc.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.springmvc.domain.boardDTO;
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
	public void boardlist(Model model,HttpServletRequest req) {
		br.boardlist(model,req);
		
	}

	@Override //선택된 게시글 상세 페이지 가져오기
	public void requestboardview(Model model) {
		br.requestboardview(model);
		
	}

	@Override
	public void updateboard(boardDTO board, HttpServletRequest req) {
		br.updateboard(board, req);
		
	}

	@Override
	public boardDTO getByNum(int num) {
		boardDTO board = br.getByNum(num);		
		return board;
	}

	@Override
	public void deleteboard(String num) {
		br.deleteboard(num);
		
	}
	
	
	
	
}
