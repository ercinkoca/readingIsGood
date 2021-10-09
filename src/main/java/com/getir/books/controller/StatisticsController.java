package com.getir.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.getir.books.exceptions.ApiResponseBuilder;
import com.getir.books.model.ApiResponse;
import com.getir.books.service.StatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

	@Autowired
	private StatisticsService statisticsService;

	@GetMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> getCustomerStatistic(@PathVariable Long id) {
		ApiResponse response = statisticsService.getCustomerStatistic(id);
		return ApiResponseBuilder.successResponse(response);
	}

}
