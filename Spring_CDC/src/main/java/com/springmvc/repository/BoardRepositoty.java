package com.springmvc.repository;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springmvc.domain.boardDTO;
import com.springmvc.domain.criteria;
import com.springmvc.domain.pageDTO;

public interface BoardRepositoty {
	
	//글쓰기 기능
	public void writeboard(boardDTO board,HttpServletRequest req);
	//게시글 가져오기 기능
	public List<boardDTO> getboardlist(pageDTO page);
	//게시글 작성시간 비교하는 기능
	public String caltime(String time);
	//게시글 상세페이지 가져오기 기능
	public boardDTO getboardview(int num);
	//게시글 조회수 증가 기능
	public void updatehit(int num);
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
