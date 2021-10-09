package com.getir.books.model;

import java.io.Serializable;
import java.util.List;

public class OrderRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5334445317931806287L;

	private Long customerId;
	private List<OrderModel> orderList;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<OrderModel> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderModel> orderList) {
		this.orderList = orderList;
	}

}
