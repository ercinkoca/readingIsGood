package com.getir.books.service;

import java.util.Date;

import com.getir.books.model.ApiResponse;
import com.getir.books.model.OrderRequest;

public interface OrderService {

	ApiResponse create(OrderRequest request);

	ApiResponse getOrderById(Long id);

	ApiResponse getByDates(Date startDate, Date endDate);

}
