package com.getir.books.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.getir.books.model.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ApiResponse response = new ApiResponse(500, ex.getMessage(), null);
		return ApiResponseBuilder.failResponse(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(BadRequestException.class)
	public final ResponseEntity<ApiResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
		ApiResponse response = new ApiResponse(400, ex.getMessage(), null);
		return ApiResponseBuilder.failResponse(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BusinessException.class)
	public final ResponseEntity<ApiResponse> handleBooksExceptions(BusinessException ex, WebRequest request) {
		ApiResponse response = new ApiResponse(ex.getErrCode(), ex.getMessage(), null);
		return ApiResponseBuilder.failResponse(response, HttpStatus.BAD_REQUEST);
	}

}
