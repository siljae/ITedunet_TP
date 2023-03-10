package com.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.cart;
import com.springmvc.repository.cartRepository;
@Service
public class cartServiceImpl implements cartService {
	@Autowired
	private cartRepository cartrepository;
	
	@Override
	public cart create(cart Cart) {
		// 장바구니 저장소 객체에 생성한 장바구니 가져와서 반환 (장바구니 자제 반환)
		return cartrepository.create(Cart);
	}

	@Override
	public cart read(String cartId) {
		// 저장소 객체에서 장바구니 id에 대해 장바구니에 등록된 모든 정보를 가져와 반환 (장바구니 안 상품 반환)
		return cartrepository.read(cartId);
	}
	public void update(String cartId, cart Cart) {
		cartrepository.update(cartId, Cart);
	}
	
	public void delete(String cartId) {
		cartrepository.delete(cartId);
	}

}
