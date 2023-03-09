package com.springmvc.service;

import com.springmvc.domain.cart;

public interface cartService {
	cart create(cart Cart);
	cart read(String cartId);
}
