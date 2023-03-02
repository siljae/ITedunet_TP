package com.springmvc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springmvc.domain.memberDTO;
@Repository
public class MemberMapper implements RowMapper<memberDTO>{

	@Override
	public memberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		memberDTO dto = new memberDTO();
		dto.setEmail(rs.getString(1));
		dto.setName(rs.getString(2));		
		return dto;
	}
	
}
