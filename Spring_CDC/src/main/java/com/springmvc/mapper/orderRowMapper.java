package com.springmvc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springmvc.domain.orderDTO;

public class orderRowMapper implements RowMapper<orderDTO>{
   public orderDTO mapRow(ResultSet rs, int rowNum) throws SQLException{
      orderDTO order = new orderDTO();
      
      order.setNum(rs.getInt(1));
      order.setOnum(rs.getString(2));
      order.setProductId(rs.getString(3));
      order.setName(rs.getString(4));
      order.setQuantity(rs.getInt(5));
      order.setOrderdate(rs.getString(6));
      order.setSumprice(rs.getInt(7));
      order.setTfilename(rs.getString(8));
      order.setPrice(rs.getInt(9));
      
      return order;
   }

}