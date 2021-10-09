package com.getir.books.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Statistics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9182810780145012397L;

	private String month;
	private int totalOrderCount;
	private int totalBookCount;
	private BigDecimal totalPurchasedAmount;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getTotalOrderCount() {
		return totalOrderCount;
	}

	public void setTotalOrderCount(int totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}

	public int getTotalBookCount() {
		return totalBookCount;
	}

	public void setTotalBookCount(int totalBookCount) {
		this.totalBookCount = totalBookCount;
	}

	public BigDecimal getTotalPurchasedAmount() {
		return totalPurchasedAmount;
	}

	public void setTotalPurchasedAmount(BigDecimal totalPurchasedAmount) {
		this.totalPurchasedAmount = totalPurchasedAmount;
	}

}
