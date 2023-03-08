package com.springmvc.domain;

import java.util.HashMap;
import java.util.Map;

public class cart {
	private String cartId;
	private Map<String, cartitem> cartitems;
	private int grandTotal;
	
	public cart() {
		cartitems = new HashMap<String, cartitem>();
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

	public Map<String, cartitem> getCartitems() {
		return cartitems;
	}

	public void setCartitems(Map<String, cartitem> cartitems) {
		this.cartitems = cartitems;
	}

	public int getGrandTotal() {
		return grandTotal;
	}

	
	
	
}
