package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.cart;
import com.springmvc.domain.cartDTO;
import com.springmvc.repository.cartRepository;
@Service
public class cartServiceImpl implements cartService {
	@Autowired
	private cartRepository cr;
	
	public void setNewCart(cartDTO cart) {
		cr.setNewCart(cart);
	};
	
	public List<cartDTO> getAllCartList(){
		return cr.getAllCartList();
	}
	
	public cartDTO getMemberByNmae(String m_name) {
		cartDTO MemberByName = cr.getMemberByNmae(m_name);
		return MemberByName;
	};
}
