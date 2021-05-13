package com.avans.eindproject.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Book")
public class Book {

	private int id;
	private int ISBN;
	private String bookName;
	private String authorName;
	private boolean toSale;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int ISBN) {
		this.ISBN = ISBN;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public boolean isToSale() {
		return toSale;
	}

	public void setToSale(boolean toSale) {
		this.toSale = toSale;
	}

}
