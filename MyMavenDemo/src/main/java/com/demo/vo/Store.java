package com.demo.vo;

import java.util.List;

public class Store {
	private List<Book> book;
	private Bicycle bicycle;

	public Store() {
		super();
	}

	public Store(List<Book> book, Bicycle bicycle) {
		super();
		this.book = book;
		this.bicycle = bicycle;
	}

	public List<Book> getBook() {
		return book;
	}

	public void setBook(List<Book> book) {
		this.book = book;
	}

	public Bicycle getBicycle() {
		return bicycle;
	}

	public void setBicycle(Bicycle bicycle) {
		this.bicycle = bicycle;
	}

}
