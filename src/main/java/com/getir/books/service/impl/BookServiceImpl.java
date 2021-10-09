package com.getir.books.service.impl;

import java.math.BigDecimal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getir.books.entity.Book;
import com.getir.books.exceptions.BusinessException;
import com.getir.books.model.ApiResponse;
import com.getir.books.model.BookRequest;
import com.getir.books.repository.BookRepository;
import com.getir.books.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Override
	public ApiResponse create(BookRequest request) {
		try {
			validateBookRequest(request);
			Book book = new Book();
			book.setBookName(request.getBookName());
			book.setBookCount(request.getBookCount());
			book.setBookPrice(request.getBookPrice());
			bookRepository.save(book);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		return new ApiResponse(200, "Book Created.", null);
	}

	@Override
	public ApiResponse update(BookRequest request) {
		try {
			Book book = bookRepository.findByBookName(request.getBookName());
			if (book == null) {
				throw new BusinessException(404, "Book can not found");
			}
			book.setBookCount(request.getBookCount());
			book.setBookPrice(request.getBookPrice());
			bookRepository.save(book);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		return new ApiResponse(200, "Book updated.", null);
	}

	private void validateBookRequest(BookRequest request) {
		if (request.getBookName().isEmpty()) {
			throw new BusinessException(401, "Book name can not be null");
		}
		Book book = bookRepository.findByBookName(request.getBookName());
		if (book != null) {
			throw new BusinessException(408, "Book is already registered!");
		}
		if (request.getBookCount() == null || request.getBookCount() == 0) {
			throw new BusinessException(402, "Please enter a book count more than zero");
		}
		if (request.getBookPrice() == null || request.getBookPrice().equals(new BigDecimal(0))) {
			throw new BusinessException(403, "Please enter a book price more than zero");
		}
	}

}
