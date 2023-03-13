package com.springmvc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.domain.productDTO;

public class productRowMapper implements RowMapper<productDTO>{
	public productDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
		productDTO product = new productDTO();
		product.setProductId(rs.getString(1));
		product.setNum(rs.getInt(2));
		product.setName(rs.getString(3));
		product.setUnitprice(rs.getInt(4));
		
		
		
	}

}
