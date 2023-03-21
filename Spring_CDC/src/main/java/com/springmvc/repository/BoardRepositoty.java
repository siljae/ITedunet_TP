package com.springmvc.repository;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springmvc.domain.boardDTO;

public interface BoardRepositoty {

	public void writeboard(boardDTO board,HttpServletRequest req);
	public void boardlist(Model model,HttpServletRequest req);
	public int getlistcount(String animal, String content);
	public ArrayList<boardDTO> getboardlist(int pageNum, int limit, String animal, String content, String sort);
	public String caltime(String time);
	public void requestboardview(Model model);
	public boardDTO getboardbynum(int num, int pageNum);
	public void updateboard(boardDTO board,HttpServletRequest req);
	public boardDTO getByNum(int num);
	public void deleteboard(String num);
	public void search(Model model,HttpServletRequest req);
}
