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
	
	public void updateGrandTotal() {
		grandTotal = 0;
		for (cartitem item : cartitems.values()) {
			grandTotal = grandTotal + item.getTotalPrice();
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
	
	
}
