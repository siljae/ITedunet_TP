package com.springmvc.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.springmvc.domain.cart;

@Repository
public class cartRepositoryImpl implements cartRepository {
	private Map<String, cart> listOfCarts;
	
	public cartRepositoryImpl() {
		listOfCarts = new HashMap<String, cart>();
	}
	
	public cart create(cart Cart) {
		if ( listOfCarts.keySet().contains(Cart.getCartId())) {
			throw new IllegalArgumentException(String.format("장바구니를 생성할 수 없습니다. 장바구니 id(%)가 존재합니다.", Cart.getCartId()));
		}
		
		listOfCarts.put(Cart.getCartId(), Cart);
		return Cart;
	}
	public cart read(String cartId) {
		return listOfCarts.get(cartId);
	}
}
