package com.springmvc.domain;

public class cartitem {
	private productDTO product;
	private int quantity;
	private int totalPrice;
	
	public cartitem() {
		
	}
	
	public cartitem(productDTO product) {
		super();
		this.product = product;
		this.quantity = 1;
		this.totalPrice = product.getUnitprice();
	}

	public productDTO getProduct() {
		return product;
	}

	public void setProduct(productDTO product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	
	public void updateTotalPrice() {
		totalPrice = this.product.getUnitprice() * this.quantity;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		cartitem other = (cartitem) obj;
		if (product == null) {
			if(other.product != null)
				return false;
		}else if (!product.equals(other.product))
			return false;
		return true;
	}
}
