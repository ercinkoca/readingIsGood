package com.getir.books.model;

import java.io.Serializable;
import java.util.List;

public class StatisticsResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9182810780145012397L;
	
	private List<Statistics> statisticList;

	public List<Statistics> getStatisticList() {
		return statisticList;
	}

	public void setStatisticList(List<Statistics> statisticList) {
		this.statisticList = statisticList;
	}
	
	

}
