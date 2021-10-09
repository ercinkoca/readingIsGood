package com.getir.books.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5334445317931806287L;

	private Long bookId;
	private String orderName;
	private Date startDate;
	private Date endDate;
	private int bookCount;

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

}
