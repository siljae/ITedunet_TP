package com.springmvc.service;

import com.springmvc.domain.cart;

public interface cartService {
	cart create(cart Cart);
	cart read(String cartId);
	void update(String cartId, cart Cart);
	void delete(String cartId);
}
