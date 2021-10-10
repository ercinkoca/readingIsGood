package com.getir.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getir.books.exceptions.ApiResponseBuilder;
import com.getir.books.model.ApiResponse;
import com.getir.books.model.DateRequest;
import com.getir.books.model.OrderRequest;
import com.getir.books.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> createOrder(@RequestBody OrderRequest request) {
		ApiResponse response = orderService.create(request);
		return ApiResponseBuilder.successResponse(response);
	}

	@GetMapping(value = "/getById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long id) {
		ApiResponse response = orderService.getOrderById(id);
		return ApiResponseBuilder.successResponse(response);
	}

	@PostMapping(value = "/getByDates", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> getOrdersByDate(@RequestBody DateRequest request) {
		ApiResponse response = orderService.getByDates(request.getStartDate(), request.getEndDate());
		return ApiResponseBuilder.successResponse(response);
	}

}
