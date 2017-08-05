package com.demo.vo;

import java.util.List;

public class ArrayVo {
	private String id;
	private List<String> sex;
	private String[] color;

	public ArrayVo() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getSex() {
		return sex;
	}

	public void setSex(List<String> sex) {
		this.sex = sex;
	}

	public String[] getColor() {
		return color;
	}

	public void setColor(String[] color) {
		this.color = color;
	}
}
