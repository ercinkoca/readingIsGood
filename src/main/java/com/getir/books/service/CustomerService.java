package com.getir.books.service;


import java.util.List;

import com.getir.books.model.ApiResponse;
import com.getir.books.model.CustomerRequest;
import com.getir.books.model.OrderModel;


public interface CustomerService {

	ApiResponse create(CustomerRequest request);

	ApiResponse update(CustomerRequest request);

	ApiResponse getAllOrders(Long id);

}
