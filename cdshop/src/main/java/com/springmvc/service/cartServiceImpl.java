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
		return cartrepository.create(Cart);
	}

	@Override
	public cart read(String cartId) {
		return cartrepository.read(cartId);
	}

}
