package com.springmvc.repository;

import java.util.List;


import com.springmvc.domain.productDTO;

public interface productRepository {
	List<productDTO> getAllProductList();
	List<productDTO> getProductListByCategory(String category);
	productDTO getProductById(String productId);
	public void setNewProduct(productDTO product);
//	public List<productDTO> selectAllProducts();
}
