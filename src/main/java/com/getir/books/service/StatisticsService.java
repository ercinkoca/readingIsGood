package com.getir.books.service;

import com.getir.books.model.ApiResponse;
import com.getir.books.model.Statistics;
import com.getir.books.model.StatisticsResponse;

public interface StatisticsService {

	ApiResponse getCustomerStatistic(Long id);

}
