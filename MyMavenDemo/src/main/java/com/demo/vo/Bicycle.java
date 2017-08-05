package com.demo.vo;

public class Bicycle {
	private Double price;
	private String color;

	public Bicycle() {
		super();
	}

	public Bicycle(Double price, String color) {
		super();
		this.price = price;
		this.color = color;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
