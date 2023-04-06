package com.springmvc.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class orderDTO {
   
   private int num;
   //뷰로 전달받을 값 
   private String onum;
   private String productId;
   private String name;
   private int quantity;
   //DB에서 꺼내올 값
   private int price;
   private String orderdate;
   private int sumprice;
   private String tfilename;
   private int totalprice;
   private List<productDTO> products;
   
   public int getNum() {
      return num;
   }
   public void setNum(int num) {
      this.num = num;
   }
   
   public String getOnum() {
	return onum;
   }
   public void setOnum(String onum) {
		this.onum = onum;
   }
   public String getProductId() {
	      return productId;
   }
   public void setProductId(String productId) {
      this.productId = productId;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getQuantity() {
      return quantity;
   }
   public void setQuantity(int quantity) {
      this.quantity = quantity;
   }
   public String getOrderdate() {
      return orderdate;
   }
   public void setOrderdate(String orderdate) {
      this.orderdate = orderdate;
   }
   public String getTfilename() {
      return tfilename;
   }
   public void setTfilename(String tfilename) {
      this.tfilename = tfilename;
   }
   public int getSumprice() {
      return sumprice;
   }
   public void setSumprice(int price) {
      this.sumprice = price;
   }
   public int getTotalprice() {
      return totalprice;
   }
   public void setTotalprice(int totalprice) {
      this.totalprice = totalprice;
   }
   public int getPrice() {
	return price;
   }
   public void setPrice(int price) {
	this.price = price;
   }
	public List<productDTO> getProducts() {
		return products;
	}
	public void setProducts(List<productDTO> products) {
		this.products = products;
	}
   
   
   
}