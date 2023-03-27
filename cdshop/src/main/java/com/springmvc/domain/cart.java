package com.springmvc.domain;

import java.util.HashMap;
import java.util.Map;

public class cart {
	private String cartId;
	private Map<String, cartDTO> cartdto;
	private int grandTotal;
	
	public cart() {
		cartdto = new HashMap<String, cartDTO>();
		grandTotal = 0;
	}
	
	public cart(String cartId) {
		this();
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<String, cartDTO> getCartdto() {
		return cartdto;
	}

	public void setCartdto(Map<String, cartDTO> cartdto) {
		this.cartdto = cartdto;
	}

	public int getGrandTotal() {
		return grandTotal;
	}
	
	public void updateGrandTotal() {
		grandTotal = 0;
		for (cartDTO item : cartdto.values()) {
			grandTotal = grandTotal + item.get();
		}
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartId == null )? 0 : cartId.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		cart other = (cart) obj;
		if (cartId == null) {
			if (other.cartId != null)
				return false;
		}else if (!cartId.equals(other.cartId))
			return false;
		return true;
	}
	
	public void addcartitem(cartitem item) {
		String productId = item.getProduct().getProductId();
		// 현재 등록하기 위한 상품 id 가져오기
		
		if(cartitems.containsKey(productId)) {
			//상품 id가 cartitem 객체에 등록되있는지 여부 확인
			cartitem CartItem = cartitems.get(productId);
			//등록된 상품 id에 대한 정보 가져옴
			CartItem.setQuantity(CartItem.getQuantity() + item.getQuantity());
			// 등록된 상품 id의 개수 추가 저장
			cartitems.put(productId, CartItem);
			// 등록된 상품 id에 대한 변경 정보 CartItem에 저장
		}else {
			cartitems.put(productId, item);
			// 상품 id에 대한 상품 정보(item) 저장
		}
		updateGrandTotal();
		//총액 갱신
	}
	
	public void removecartitem(cartitem item) {
		String productId = item.getProduct().getProductId();
		cartitems.remove(productId);
		updateGrandTotal(); //총액 갱신
	}
}
