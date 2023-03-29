package com.springmvc.repository;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springmvc.domain.boardDTO;
import com.springmvc.domain.criteria;
import com.springmvc.domain.pageDTO;

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
	//게시글 검색
	public List<boardDTO> search(String content,pageDTO page);
	public void recom(String num, String recom);
	public List<boardDTO> recomboard();
	//전체 게시글 개수
	public int getallcount();
	//검색한 게시글 개수
	public int getcount(String content);
}
