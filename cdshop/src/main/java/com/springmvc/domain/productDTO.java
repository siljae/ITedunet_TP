package com.springmvc.domain;

import java.util.Date;

public class productDTO {
	private String productId;
	private int num;
	private String name;
	private String category;
	private String titlement;
	private String simpledescription;
	private String manufacturer;
	private int unitprice;
	private long unitsinstock;
	private String detailfilename;
	private String titlefilename;
	private Date date;
	private int hit;
	
	public productDTO() {
		super();
	} 

	
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitlement() {
		return titlement;
	}

	public void setTitlement(String titlement) {
		this.titlement = titlement;
	}

	public String getSimpledescription() {
		return simpledescription;
	}

	public void setSimpledescription(String simpledescription) {
		this.simpledescription = simpledescription;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(int unitprice) {
		this.unitprice = unitprice;
	}

	public long getUnitsinstock() {
		return unitsinstock;
	}

	public void setUnitsinstock(long unitsinstock) {
		this.unitsinstock = unitsinstock;
	}

	public String getDetailfilename() {
		return detailfilename;
	}

	public void setDetailfilename(String detailfilename) {
		this.detailfilename = detailfilename;
	}

	public String getTitlefilename() {
		return titlefilename;
	}

	public void setTitlefilename(String titlefilename) {
		this.titlefilename = titlefilename;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}
