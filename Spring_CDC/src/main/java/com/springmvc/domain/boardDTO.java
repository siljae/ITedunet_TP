package com.springmvc.domain;

import org.springframework.web.multipart.MultipartFile;

public class boardDTO {
	private int num;
	private String name;
	private String title;
	private String content;
	private String regist_day;
	private int hit;
	private String animal_type;	
	private MultipartFile fileimage;

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
	public String getRegist_day() {
		return regist_day;
	}
	public void setRegist_day(String regist_day) {
		this.regist_day = regist_day;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getAnimal_type() {
		return animal_type;
	}
	public void setAnimal_type(String animal_type) {
		this.animal_type = animal_type;
	}
	public MultipartFile getFileimage() {
		return fileimage;
	}
	public void setFileimage(MultipartFile fileimage) {
		this.fileimage = fileimage;
	}
	
}

