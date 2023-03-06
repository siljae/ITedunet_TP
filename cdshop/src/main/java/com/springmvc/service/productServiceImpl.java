package com.springmvc.service;

import java.util.List;

import com.springmvc.domain.productDTO;
import com.springmvc.repository.productRepository;

public class productServiceImpl implements productService {
	private productRepository productrepository;
	
	public List<productDTO> getAllProductList(){
		return productrepository.getAllProductList();
	}
	
	public List<productDTO> getProductListByCategory(String category){
		List<productDTO> productByCategory = productrepository.getProductListByCategory(category);
		return productByCategory;
	}
	
}
