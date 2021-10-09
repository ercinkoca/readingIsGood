package com.getir.books.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getir.books.entity.Book;
import com.getir.books.entity.Customer;
import com.getir.books.entity.Order;
import com.getir.books.exceptions.BusinessException;
import com.getir.books.model.ApiResponse;
import com.getir.books.model.OrderModel;
import com.getir.books.model.OrderRequest;
import com.getir.books.repository.BookRepository;
import com.getir.books.repository.CustomerRepository;
import com.getir.books.repository.OrderRepository;
import com.getir.books.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BookRepository bookRepository;

	@Override
	public ApiResponse create(OrderRequest request) {
		try {
			validateOrderRequest(request);
			Customer customer = customerRepository.findByCustomerId(request.getCustomerId());

			if (customer == null) {
				throw new BusinessException(112, "Customer can not be found");
			}

			for (OrderModel om : request.getOrderList()) {
				Book book = bookControl(om.getBookId(), om.getBookCount());
				Order order = new Order();
				order.setCustomer(customer);
				order.setBook(book);
				order.setEndDate(om.getEndDate());
				order.setStartDate(om.getStartDate());
				order.setTotalAmount(book.getBookPrice().multiply(new BigDecimal(om.getBookCount())));
				order.setOrderName(om.getOrderName());
				order.setBookCount(om.getBookCount());
				order.setOrderMonth();
				orderRepository.save(order);
			}

		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		return new ApiResponse(200, "Order Created.", null);
	}

	@Override
	public ApiResponse getOrderById(Long id) {

		try {
			Order order = orderRepository.findByOrderId(id);
			if (order == null) {
				throw new BusinessException(113, "Order Cannot found.");
			}
			OrderModel om = new OrderModel();
			om.setBookId(order.getBook().getBookId());
			om.setEndDate(order.getEndDate());
			om.setOrderName(order.getOrderName());
			om.setStartDate(order.getStartDate());
			om.setBookCount(order.getBookCount());
			return new ApiResponse(200, "Successfull", om);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
	}

	// TODO hataya bakilacak.
	@Override
	public ApiResponse getByDates(String startDate, String endDate) {
		try {
			List<Order> orderList = orderRepository.findByDates(startDate, endDate);
			List<OrderModel> responseList = new ArrayList<>();
			if (orderList.isEmpty()) {
				throw new BusinessException(114, "OrderList can not found");
			}
			for (Order o : orderList) {
				OrderModel om = new OrderModel();
				om.setBookId(o.getBook().getBookId());
				om.setOrderName(o.getOrderName());
				om.setStartDate(o.getStartDate());
				om.setEndDate(o.getEndDate());
				responseList.add(om);
			}
			return new ApiResponse(200, "Successfull", responseList);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
	}

	private void validateOrderRequest(OrderRequest request) {
		List<OrderModel> orderList = request.getOrderList();
		if (request.getCustomerId() == null || request.getCustomerId() == 0) {
			throw new BusinessException(115, "Customer id can not be null");
		}

		for (OrderModel om : orderList) {
			if (om.getBookId() == null || om.getBookId() == 0) {
				throw new BusinessException(116, "Book id can not be null");
			}
			if (om.getStartDate() == null) {
				throw new BusinessException(117, "Start date can not be null");
			}
			if (om.getEndDate() == null) {
				throw new BusinessException(118, "End date can not be null");
			}
			if (om.getBookCount() <= 0) {
				throw new BusinessException(119, "Book count can not be zero or minus");
			}
		}
	}

	private Book bookControl(Long bookId, int orderBookCount) {
		Book book = bookRepository.findByBookId(bookId);
		if (book.getBookCount() - orderBookCount <= 0) {
			throw new BusinessException(120, "There is not enough book on the stocks");
		}
		book.setBookCount(book.getBookCount() - orderBookCount);
		bookRepository.save(book);
		return book;
	}

}
