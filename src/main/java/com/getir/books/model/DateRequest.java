package com.getir.books.model;

import java.io.Serializable;
import java.util.Date;

public class DateRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4514504290509068479L;

	private Date startDate;
	private Date endDate;

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

}
