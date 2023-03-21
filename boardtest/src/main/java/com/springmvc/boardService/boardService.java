package com.springmvc.boardService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springmvc.domain.boardDTO;

public interface boardService {

	public List<boardDTO> getboardlist();
	public void setboard(boardDTO board,HttpServletRequest req);
	public void updateboard(boardDTO board, HttpServletRequest req);
	public void deleteboard(int num);
	public String calTime(String time);
	public boardDTO view(int num);
	
}
