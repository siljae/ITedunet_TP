package com.springmvc.repository;

import java.util.ArrayList;
import java.util.List;

import com.springmvc.domain.productDTO;

public class productRepositoryImpl implements productRepository {
	private List<productDTO> listOfProduct = new ArrayList<productDTO>();
	
	public List<productDTO> getAllProductList(){
		return listOfProduct;
	}
	
	public List<productDTO> getProductListByCategory (String category){
		List<productDTO> productByCategory = new ArrayList<productDTO>();
		for(int i = 0; i < listOfProduct.size(); i++) {
			productDTO productdto = listOfProduct.get(i);
			if(category.equalsIgnoreCase(productdto.getCategory()))
				productByCategory.add(productdto);
		}
		return productByCategory;
	}
	
	
}
