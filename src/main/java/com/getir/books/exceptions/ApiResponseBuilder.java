package com.getir.books.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.getir.books.model.ApiResponse;

public class ApiResponseBuilder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4667387123478778947L;

	public static ResponseEntity<ApiResponse> successResponse(ApiResponse apiResponse) {
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	public static ResponseEntity<ApiResponse> failResponse(ApiResponse apiResponse, HttpStatus status) {
		return new ResponseEntity<>(apiResponse, status);
	}

}
