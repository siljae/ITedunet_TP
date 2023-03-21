package com.springmvc.domain;

import org.springframework.web.multipart.MultipartFile;

public class boardDTO {
	
	private int num;
	private String title;
	private String content;
	private MultipartFile img;
	private String imgname;
	private String regist_day;
	private String calregist_day;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MultipartFile getImg() {
		return img;
	}
	public void setImg(MultipartFile img) {
		this.img = img;
	}
	
	public String getImgname() {
		return imgname;
	}
	public void setImgname(String imgname) {
		this.imgname = imgname;
	}
	public String getRegist_day() {
		return regist_day;
	}
	public void setRegist_day(String regist_day) {
		this.regist_day = regist_day;
	}
	public String getCalregist_day() {
		return calregist_day;
	}
	public void setCalregist_day(String calregist_day) {
		this.calregist_day = calregist_day;
	}
	
	
	
	
}
