package com.springmvc.domain;

public class cartDTO {
	private int cartid;
	private String name;
	private String productid;
	private int cartqnt;
	private String titlefilename;
	
	public cartDTO() {
		super();
	}

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public int getCartqnt() {
		return cartqnt;
	}

	public void setCartqnt(int cartqnt) {
		this.cartqnt = cartqnt;
	}

	public String getTitlefilename() {
		return titlefilename;
	}

	public void setTitlefilename(String titlefilename) {
		this.titlefilename = titlefilename;
	}
	
	
}
