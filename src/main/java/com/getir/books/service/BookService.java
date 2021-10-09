package com.getir.books.service;

import com.getir.books.model.ApiResponse;
import com.getir.books.model.BookRequest;

public interface BookService {

	ApiResponse create(BookRequest request);

	ApiResponse update(BookRequest request);

}
