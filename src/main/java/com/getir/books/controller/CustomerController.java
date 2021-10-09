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
import com.getir.books.model.CustomerRequest;
import com.getir.books.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerRequest request) {
		ApiResponse response = customerService.create(request);
		return ApiResponseBuilder.successResponse(response);
	}

	@PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> updateCustomer(@RequestBody CustomerRequest request) {
		ApiResponse response = customerService.update(request);
		return ApiResponseBuilder.successResponse(response);
	}

	@GetMapping(value = "/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> getAllOrders(@PathVariable Long id) {
		ApiResponse response = customerService.getAllOrders(id);
		return ApiResponseBuilder.successResponse(response);
	}
}
