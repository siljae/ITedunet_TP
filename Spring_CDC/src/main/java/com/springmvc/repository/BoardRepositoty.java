package com.springmvc.repository;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.springmvc.domain.boardDTO;

public interface BoardRepositoty {

	public void writeboard(boardDTO board,HttpServletRequest req);
	public void boardlist(Model model);
}
