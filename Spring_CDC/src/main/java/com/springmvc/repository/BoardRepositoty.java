package com.springmvc.repository;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.springmvc.domain.boardDTO;
import com.springmvc.domain.pageDTO;

public interface BoardRepositoty {
	
	//글쓰기 기능
	public void writeboard(boardDTO board,HttpServletRequest req);
	//게시글 가져오기 기능
	public List<boardDTO> getboardlist(pageDTO page);
	//게시글 정렬 기능
	public List<boardDTO> getsortboardlist(pageDTO page, String sort);
	//게시글 작성시간 비교하는 기능
	public String caltime(String time);
	//게시글 상세페이지 가져오기 기능
	public boardDTO getboardview(int num, HttpServletRequest req);
	//게시글 조회수 증가 기능
	public void updatehit(int num);
	//게시글 삭제
	public void deleteboard(String num);
	//게시글 수정
	public void updateboard(boardDTO board, HttpServletRequest req);
	//게시글 검색
	public List<boardDTO> search(String content,pageDTO page);
	//게시글 추천
	public void recom(int num, String recom);
	//인기글(추천수 10개이상) 3개 가져오기
	public List<boardDTO> getrecomboard();
	//전체 게시글 개수
	public int getallcount();
	//검색한 게시글 개수
	public int getcount(String content);
}
