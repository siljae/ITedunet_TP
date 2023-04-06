package com.springmvc.boardService;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.domain.boardDTO;

public class boardRowMapper implements RowMapper<boardDTO> {

	@Override
	public boardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		boardDTO board = new boardDTO();
		board.setNum(rs.getInt(1));
		board.setTitle(rs.getString(2));
		board.setContent(rs.getString(3));
		board.setImgname(rs.getString(4));
		board.setRegist_day(rs.getString(5));
		return board;
	}
	

}
