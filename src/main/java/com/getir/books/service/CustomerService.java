package com.getir.books.service;

import com.getir.books.model.ApiResponse;
import com.getir.books.model.CustomerRequest;

public interface CustomerService {

	ApiResponse create(CustomerRequest request);

	ApiResponse update(CustomerRequest request);

	ApiResponse getAllOrders(Long id);

}
