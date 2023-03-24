package com.springmvc.domain;

import org.springframework.web.multipart.MultipartFile;

public class cartDTO {
	
	private int cartId;
	private String m_name;
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
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
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
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
