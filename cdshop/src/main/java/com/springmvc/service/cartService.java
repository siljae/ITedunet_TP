package com.springmvc.service;

import java.util.List;

import com.springmvc.domain.cart;
import com.springmvc.domain.cartDTO;

public interface cartService {
	
	public void setNewCart(cartDTO cart);
	public List<cartDTO> getAllCartList();
	public cartDTO getMemberByNmae(String m_name);
}
