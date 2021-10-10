package com.getir.books.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class BookRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8235195206824978040L;
	
	public BookRequest(String bookName, BigDecimal bookPrice, Integer bookCount) {
		super();
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.bookCount = bookCount;
	}
	
	public BookRequest() {
		super();
	}

	private String bookName;
	private BigDecimal bookPrice;
	private Integer bookCount;

	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public BigDecimal getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(BigDecimal bookPrice) {
		this.bookPrice = bookPrice;
	}
	public Integer getBookCount() {
		return bookCount;
	}
	public void setBookCount(Integer bookCount) {
		this.bookCount = bookCount;
	}
	
	
	
	

}
