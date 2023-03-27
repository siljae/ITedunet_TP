package com.springmvc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.domain.cartDTO;
import com.springmvc.domain.productDTO;

public class cartRowMapper implements RowMapper<cartDTO>{
	public cartDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
		cartDTO cart = new cartDTO();
		
		cart.setCartId(rs.getInt(1));
		cart.setName(rs.getString(2));
		cart.setProductId(rs.getString(3));
		cart.setQuantity(rs.getInt(4));
		cart.setTfilename(rs.getString(5));
		
		return cart;
	}
}
