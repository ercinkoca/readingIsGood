package com.getir.books.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getir.books.entity.Customer;
import com.getir.books.entity.Order;
import com.getir.books.exceptions.BusinessException;
import com.getir.books.model.ApiResponse;
import com.getir.books.model.CustomerRequest;
import com.getir.books.model.OrderModel;
import com.getir.books.repository.CustomerRepository;
import com.getir.books.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LogManager.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public ApiResponse create(CustomerRequest request) {
		try {
			validateCustomerRequest(request);
			emailCheck(request);
			Customer customer = new Customer();
			customer.setCustomerName(request.getCustomerName());
			customer.setCustomerSurname(request.getCustomerSurname());
			customer.setAddress(request.getAddress());
			customer.setEmail(request.getEmail());
			customerRepository.save(customer);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		return new ApiResponse(200, "Customer Created.", null);
	}

	@Override
	public ApiResponse update(CustomerRequest request) {
		try {
			validateCustomerRequest(request);
			Customer customer = customerRepository.findByEmail(request.getEmail());
			customer.setCustomerName(request.getCustomerName());
			customer.setCustomerSurname(request.getCustomerSurname());
			customer.setAddress(request.getAddress());
			customerRepository.save(customer);
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw e;
		}
		return new ApiResponse(200, "Customer Updated.", null);
	}

	@Override
	public ApiResponse getAllOrders(Long id) {
		try {
			List<OrderModel> responseList = new ArrayList<>();
			Customer customer = customerRepository.findByCustomerId(id);
			if (customer == null) {
				throw new BusinessException(106, "Customer can not found");
			}
			List<Order> orderList = customer.getOrderList();
			for (Order o : orderList) {
				OrderModel om = new OrderModel();
				om.setBookId(o.getBook().getBookId());
				om.setEndDate(o.getEndDate());
				om.setStartDate(o.getStartDate());
				om.setOrderName(o.getOrderName());
				om.setBookCount(o.getBookCount());
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

	private void validateCustomerRequest(CustomerRequest request) {
		LOGGER.error("Request Validation Failed");
		if (request.getCustomerName().isEmpty()) {
			throw new BusinessException(107, "Customer name can not be null");
		}
		if (request.getCustomerSurname().isEmpty()) {
			throw new BusinessException(108, "Customer surname can not be null");
		}

		if (request.getEmail().isEmpty()) {
			throw new BusinessException(109, "Customer email can not be null");
		}
		if (request.getAddress().isEmpty()) {
			throw new BusinessException(110, "Customer address can not be null");
		}
	}

	private void emailCheck(CustomerRequest request) {
		List<Customer> customerList = customerRepository.findAll();
		Customer customer = customerList.parallelStream().filter(x -> x.getEmail().equals(request.getEmail()))
				.findFirst().orElse(null);
		if (customer != null) {
			LOGGER.error("Email Check Failed");
			throw new BusinessException(111, "Customer email is already registered!");
		}
	}

}
