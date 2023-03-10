package com.springmvc.domain;

import org.springframework.web.multipart.MultipartFile;

public class boardDTO {
	
	private int num;
	private String name;
	private String board_type;
	private String animal_type;
	private String title;
	private String content;
	private String regist_day;
	private int hit;
	private MultipartFile fileimage;
	private String filename;
	private String tag_src;
	private String tag_value;

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
	
	public String getBoard_type() {
		return board_type;
	}
	public void setBoard_type(String board_type) {
		this.board_type = board_type;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	public String getTag_src() {
		return tag_src;
	}
	public void setTag_src(String tag_src) {
		this.tag_src = tag_src;
	}
	public String getTag_value() {
		return tag_value;
	}
	public void setTag_value(String tag_value) {
		this.tag_value = tag_value;
	}
	@Override
	public String toString() {
		return "boardDTO [num=" + num + ", name=" + name + ", board_type=" + board_type + ", animal_type=" + animal_type
				+ ", title=" + title + ", content=" + content + ", regist_day=" + regist_day + ", hit=" + hit
				+ ", fileimage=" + fileimage + ", filename=" + filename + "]";
	}
	
	
	
	
	
	
	
	
	
}

