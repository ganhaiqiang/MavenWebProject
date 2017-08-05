package com.demo.vo;

public class Book {
	private String author;
	private String title;
	private String category;
	private Double price;
	private String isbn;

	public Book() {
		super();
	}

	public Book(String author, String title, String category, Double price, String isbn) {
		super();
		this.author = author;
		this.title = title;
		this.category = category;
		this.price = price;
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}
