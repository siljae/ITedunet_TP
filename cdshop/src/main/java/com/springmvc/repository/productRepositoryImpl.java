package com.springmvc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.springmvc.domain.productDTO;

public class productRepositoryImpl implements productRepository {
	private List<productDTO> listOfProduct = new ArrayList<productDTO>();
	
	public List<productDTO> getAllProductList(){
		return listOfProduct;
	}
	
	public List<productDTO> getProductListByCategory (String category){
		List<productDTO> productByCategory = new ArrayList<productDTO>();
		for(int i = 0; i < listOfProduct.size(); i++) {
			productDTO productdto = listOfProduct.get(i);
			if(category.equalsIgnoreCase(productdto.getCategory()))
				productByCategory.add(productdto);
		}
		return productByCategory;
	}
	
	public List<productDTO> selectAllProducts(){
		
		List<productDTO> list = new ArrayList<productDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			conn = com.springmvc.database.DBConnection.getConnection();
			sql = "select * from product order by p_num desc";
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				productDTO dto = new productDTO();
				dto.setNum(rs.getInt("p_num"));
				dto.setManufacturer(rs.getString("p_manufacturer"));
				dto.setName(rs.getString("p_name"));
				dto.setUnitprice(rs.getInt("p_unitprice"));
				list.add(dto);
			}
		}catch (Exception e) {
			System.out.println("최근상품 먼저 출력하기 실패!" + e);
		}finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					rs.close();
				if(conn != null)
					rs.close();
			} catch (Exception e2) {
				System.out.println("최근상품 close 실패 !" + e2);
			}
		}
		System.out.println("최근상품 정렬 프로세스 실패");
		return list;
	}
}
