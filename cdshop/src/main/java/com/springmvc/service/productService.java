package com.springmvc.service;

import java.util.List;

import com.springmvc.domain.productDTO;

public interface productService {
	List<productDTO> getAllProductList();
	List<productDTO> getProductListByCategory(String category);
	productDTO getProductById(String productId);
}
