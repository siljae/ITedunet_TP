package com.springmvc.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.boardDTO;
import com.springmvc.repository.BoardRepositoty;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardRepositoty br;

	@Override
	public void writeboard(boardDTO board,HttpServletRequest req) {		
		br.writeboard(board,req);
	}

}
