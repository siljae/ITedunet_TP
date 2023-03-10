package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.productDTO;
import com.springmvc.repository.productRepository;

@Service
public class productServiceImpl implements productService {
	@Autowired
	private productRepository productrepository;
	
	public List<productDTO> getAllProductList(){
		return productrepository.getAllProductList();
	}
	
	public List<productDTO> getProductListByCategory(String category){
		List<productDTO> productByCategory = productrepository.getProductListByCategory(category);
		return productByCategory;
	}
	
	public productDTO getProductById(String productId) {
		productDTO productById = productrepository.getProductById(productId);
		return productById;
	}
}
