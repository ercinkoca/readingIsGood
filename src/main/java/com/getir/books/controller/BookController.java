package com.getir.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getir.books.exceptions.ApiResponseBuilder;
import com.getir.books.model.ApiResponse;
import com.getir.books.model.BookRequest;
import com.getir.books.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> createBook(@RequestBody BookRequest request) {
		ApiResponse response = bookService.create(request);
		return ApiResponseBuilder.successResponse(response);
	}

	@PatchMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> updateBook(@RequestBody BookRequest request) {
		ApiResponse response = bookService.update(request);
		return ApiResponseBuilder.successResponse(response);
	}

}
