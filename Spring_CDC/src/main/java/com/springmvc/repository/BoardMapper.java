package com.springmvc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.domain.boardDTO;

public class BoardMapper implements RowMapper<boardDTO>{

	@Override
	public boardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		boardDTO board = new boardDTO();
		board.setNum(rs.getInt(1));
		board.setBoard_type(rs.getString(2));
		board.setAnimal_type(rs.getString(3));
		board.setName(rs.getString(4));
		board.setTitle(rs.getString(5));
		board.setBoard_type(rs.getString(6));
		board.setRegist_day(rs.getString(7));
		board.setHit(rs.getInt(8));
		board.setFilename(rs.getString(9));
		return null;
	}
	
	

}
