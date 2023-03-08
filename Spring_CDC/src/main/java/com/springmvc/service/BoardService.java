package com.springmvc.service;



import javax.servlet.http.HttpServletRequest;

import com.springmvc.domain.boardDTO;

public interface BoardService {
	public void writeboard(boardDTO board,HttpServletRequest req);
}
