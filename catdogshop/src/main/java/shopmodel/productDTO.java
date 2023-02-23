package shopmodel;

import java.io.Serializable;

public class productDTO implements Serializable{
	private static final long serialVersionUID = -4274700572038677000L;
	
	private int num;
	private String name;
	private String category;
	private String titlement;
	private String simpledescripton;
	private String manufacturer;
	private int unitprice;
	private long untisinstock;
	private String detailfilename;
	private String titlefilename;
	private String date;
	private int hit;
	
	public productDTO() {
		super();
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

	public String getSimpledescripton() {
		return simpledescripton;
	}

	public void setSimpledescripton(String simpledescripton) {
		this.simpledescripton = simpledescripton;
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

	public long getUntisinstock() {
		return untisinstock;
	}

	public void setUntisinstock(long untisinstock) {
		this.untisinstock = untisinstock;
	}

	public String getDetailfilename() {
		return detailfilename;
	}

	public void setDetailfilename(String detailefilename) {
		this.detailfilename = detailefilename;
	}

	public String getTitlefilename() {
		return titlefilename;
	}

	public void setTitlefilename(String titlefilename) {
		this.titlefilename = titlefilename;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}


	
}
