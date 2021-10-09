package com.getir.books.service;

import com.getir.books.model.ApiResponse;
import com.getir.books.model.OrderRequest;

public interface OrderService {

	ApiResponse create(OrderRequest request);

	ApiResponse getOrderById(Long id);

	ApiResponse getByDates(String startDate, String endDate);

}
