package com.springmvc.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springmvc.domain.cart;
import com.springmvc.domain.cartDTO;

@Repository
public class cartRepositoryImpl implements cartRepository {
	
	private JdbcTemplate template;
	private List<cartDTO> cartlist = new ArrayList<cartDTO>();
	
	@Autowired
	public void setJdbctemplate(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	
	public void setNewCart(cartDTO cart) {
		System.out.println("name : " + cart.getName());
		String SQL = "insert into cart (ca_id, m_name, p_id, ca_qnt, p_tfilename)" + "values(?,?,?,?,?)";
		template.update(SQL, cart.getCartId(), cart.getName(), cart.getProductId(), cart.getQuantity(), cart.getTfilename());
		System.out.println("들어갔니? : " + SQL);
	}
	
	public List<cartDTO> getAllCartList(){
		String SQL = "select * from cart";
		List<cartDTO> listOfcart = template.query(SQL, new cartRowMapper());
		this.cartlist = listOfcart;
		return listOfcart;
	}
	
	public cartDTO getMemberByNmae(String name) {
		cartDTO MemberByName = null;
		for(int i = 0; i<cartlist.size(); i++) {
			cartDTO cart = cartlist.get(i);
			if(cart != null && cart.getName() != null && cart.getName().equals(name)) {
				MemberByName = cart;
				break;
			}
		}
		return MemberByName;
	}
	
	
	
//	public cart read(String cartId) {
//		//장바구니 id 이용해서 장바구니에 등록된 모든 정보 가져와 반환
//		System.out.println("cartrepository - 카트 정보 반환하니?");
//		
//		return listOfCarts.get(cartId);
//	}
//	
	
//	public void update(String cartId, cart Cart) {
//		if (!listOfCarts.keySet().contains(cartId)) {
//			throw new IllegalArgumentException(String.format("장바구니 목록을 갱신할 수 없습니다. 장바구니 id(%)가 존재하지 않습니다.", cartId));
//			// 장바구니 id가 존재하지 않을 경우 예외처리
//		}
//		listOfCarts.put(cartId, Cart);
//	}
//	
//	public void delete(String cartId) {
//		if(!listOfCarts.keySet().contains(cartId)) {
//			//장바구니 id가 존재하지 않으면 예외처리
//			throw new IllegalArgumentException(String.format("장바구니 목록을 삭제할 수 없습니다. 장바구니 id(%)가 존재하지 않습니다", cartId));
//		}
//		listOfCarts.remove(cartId);
//	}
}
