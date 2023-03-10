package com.springmvc.repository;

import com.springmvc.domain.cart;

public interface cartRepository {
	cart create(cart Cart);
	cart read(String cartId);
	void update(String cartId, cart Cart);
	void delete(String cartId);
}
