package com.springmvc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springmvc.domain.productDTO;
import com.springmvc.exception.productIdException;
@Repository
public class productRepositoryImpl implements productRepository {
	private List<productDTO> listOfProduct = new ArrayList<productDTO>();
	
	
	public List<productDTO> getAllProductList(){
		return listOfProduct;
	}
	
	
	
	public List<productDTO> getProductListByCategory (String category){
		// 상품 카테고리 일치하는 상품 가져옴
		List<productDTO> productByCategory = new ArrayList<productDTO>();
		// 일치하는 상품 분야 저장하는 객체변수 productByCategort 선언
		for(int i = 0; i < listOfProduct.size(); i++) {
			// product에 상품 목록 i번째 상품 정보 저장
			productDTO productdto = listOfProduct.get(i);
			if(category.equalsIgnoreCase(productdto.getCategory()))
				productByCategory.add(productdto);
			// 대소문자 구분 안하고 매개변수 category와 상품 분야 일치하는 상품 목록 i번째 상품 정보를 저장
		}
		return productByCategory;
		// 매개변수 category와 일치하는 상품 목록 반환
	}
	
	public productDTO getProductById(String productId) {
		productDTO productInfo = null;
		for (int i = 0; i < listOfProduct.size(); i++) {
			productDTO product = listOfProduct.get(i);
			if(product != null && product.getProductId() != null && product.getProductId().equals(productId)) {
				productInfo = product;
				break;
			}
		}
		if (productInfo == null)
			throw new productIdException(productId);
		return productInfo;
	}
	
	
	
	
//	public List<productDTO> selectAllProducts(){
//		
//		List<productDTO> list = new ArrayList<productDTO>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql;
//		try {
//			conn = com.springmvc.database.DBConnection.getConnection();
//			sql = "select * from product order by p_num desc";
//			pstmt = conn.prepareStatement(sql);
//			rs=pstmt.executeQuery();
//			while (rs.next()) {
//				productDTO dto = new productDTO();
//				dto.setNum(rs.getInt("p_num"));
//				dto.setManufacturer(rs.getString("p_manufacturer"));
//				dto.setName(rs.getString("p_name"));
//				dto.setUnitprice(rs.getInt("p_unitprice"));
//				list.add(dto);
//			}
//		}catch (Exception e) {
//			System.out.println("최근상품 먼저 출력하기 실패!" + e);
//		}finally {
//			try {
//				if(rs != null)
//					rs.close();
//				if(pstmt != null)
//					rs.close();
//				if(conn != null)
//					rs.close();
//			} catch (Exception e2) {
//				System.out.println("최근상품 close 실패 !" + e2);
//			}
//		}
//		System.out.println("최근상품 정렬 프로세스 실패");
//		return list;
//	}
}
