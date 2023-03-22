package com.springmvc.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.springmvc.domain.productDTO;

public interface productRepository {
	List<productDTO> getAllProductList();
	List<productDTO> getProductListByCategory(String category);
	productDTO getProductById(String productId);
//	Set<productDTO> getProductListByFilter(Map<String, List<String>> filter);
	public void setNewProduct(productDTO product);
//	public List<productDTO> selectAllProducts();
}
