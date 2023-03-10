package com.springmvc.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.springmvc.domain.cart;

@Repository
public class cartRepositoryImpl implements cartRepository {
	private Map<String, cart> listOfCarts;
	// 문자열과 cart 타입의 Map을 listOfCarts로 객체 선언
	
	public cartRepositoryImpl() {
		listOfCarts = new HashMap<String, cart>();
		//String과 cart 타입의 HashMap을 생성해서 listOfCarts 객체에 담음
	}
	
	public cart create(cart Cart) {
		// create() 메서드는 새로운 장바구니 생성해 장바구니id 등록하고 생성된 장바구니 객체 반환함
		// cart 타입의 Cart 생성
		if ( listOfCarts.keySet().contains(Cart.getCartId())) {
			throw new IllegalArgumentException(String.format("장바구니를 생성할 수 없습니다. 장바구니 id(%)가 존재합니다.", Cart.getCartId()));
			// 동일한 장바구니 id 존재시 예외처리
		}
		
		listOfCarts.put(Cart.getCartId(), Cart);
		return Cart;
		// listOfCarts 객체 안에 장바구니 id와 생성된 장바구니 객체 집어넣음
	}
	public cart read(String cartId) {
		//장바구니 id 이용해서 장바구니에 등록된 모든 정보 가져와 반환
		return listOfCarts.get(cartId);
	}
	
	public void update(String cartId, cart Cart) {
		if (!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("장바구니 목록을 갱신할 수 없습니다. 장바구니 id(%)가 존재하지 않습니다.", cartId));
			// 장바구니 id가 존재하지 않을 경우 예외처리
		}
		listOfCarts.put(cartId, Cart);
	}
	
	public void delete(String cartId) {
		if(!listOfCarts.keySet().contains(cartId)) {
			//장바구니 id가 존재하지 않으면 예외처리
			throw new IllegalArgumentException(String.format("장바구니 목록을 삭제할 수 없습니다. 장바구니 id(%)가 존재하지 않습니다", cartId));
		}
		listOfCarts.remove(cartId);
	}
}
