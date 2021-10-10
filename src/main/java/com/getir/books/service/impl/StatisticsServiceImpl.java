package com.getir.books.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getir.books.entity.Order;
import com.getir.books.exceptions.BusinessException;
import com.getir.books.model.ApiResponse;
import com.getir.books.model.Statistics;
import com.getir.books.model.StatisticsResponse;
import com.getir.books.repository.OrderRepository;
import com.getir.books.service.StatisticsService;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	private static final Logger LOGGER = LogManager.getLogger(StatisticsServiceImpl.class);

	@Autowired
	private OrderRepository orderRepository;

	@Override
	public ApiResponse getCustomerStatistic(Long id) {

		try {

			StatisticsResponse statisticsResponse = new StatisticsResponse();
			List<Statistics> statisticsList = new ArrayList<>();
			List<Order> orderList = orderRepository.findByCustomerId(id);

			for (int i = 1; i <= 12; i++) {
				int month = i;
				List<Order> orderMonthList = orderList.parallelStream().filter(x -> x.getOrderMonth() == month)
						.collect(Collectors.toList());
				if (!orderMonthList.isEmpty()) {
					statisticsList.add(calculateData(orderMonthList, month));
				}
			}
			statisticsResponse.setStatisticList(statisticsList);
			return new ApiResponse(200, "Successfull", statisticsResponse);

		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
	}

	public Statistics calculateData(List<Order> orderList, int month) {

		try {
			int totalBookCount = 0;
			BigDecimal totalPurchaseAmount = new BigDecimal(0);

			for (Order o : orderList) {
				totalBookCount += o.getBookCount();
				totalPurchaseAmount = totalPurchaseAmount.add(o.getTotalAmount());
			}

			Statistics response = new Statistics();
			response.setTotalBookCount(totalBookCount);
			response.setTotalOrderCount(orderList.size());
			response.setTotalPurchasedAmount(totalPurchaseAmount);

			switch (month) {
			case 1:
				response.setMonth("January");
				break;
			case 2:
				response.setMonth("February");
				break;
			case 3:
				response.setMonth("March");
				break;
			case 4:
				response.setMonth("April");
				break;
			case 5:
				response.setMonth("May");
				break;
			case 6:
				response.setMonth("June");
				break;
			case 7:
				response.setMonth("July");
				break;
			case 8:
				response.setMonth("August");
				break;
			case 9:
				response.setMonth("September");
				break;
			case 10:
				response.setMonth("October");
				break;
			case 11:
				response.setMonth("November");
				break;
			case 12:
				response.setMonth("December");
				break;
			default:
				response.setMonth("January");
			}
			return response;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
	}

}
