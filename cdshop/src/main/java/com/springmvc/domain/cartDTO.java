package com.springmvc.domain;

import org.springframework.web.multipart.MultipartFile;

public class cartDTO {
	
	private int cartId;
	private String name;
	private String productId;
	private int quantity;
	private String tfilename;
	private MultipartFile titleimage;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int unitprice) {
		this.quantity = unitprice;
	}
	public String getTfilename() {
		return tfilename;
	}
	public void setTfilename(String tfilename) {
		this.tfilename = tfilename;
	}
	public MultipartFile getTitleimage() {
		return titleimage;
	}
	public void setTitleimage(MultipartFile titleimage) {
		this.titleimage = titleimage;
	}
	
		
	
	
}
