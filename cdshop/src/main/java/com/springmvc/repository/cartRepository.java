package com.springmvc.repository;

import java.util.List;

import com.springmvc.domain.cart;
import com.springmvc.domain.cartDTO;

public interface cartRepository {
	
	public void setNewCart(cartDTO cart);
	public List<cartDTO> getAllCartList();
	public cartDTO getMemberByNmae(String m_name);
	
//	cart create(cart Cart);
//	cart read(String cartId);
//	void update(String cartId, cart Cart);
//	void delete(String cartId);
}
