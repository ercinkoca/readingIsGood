package com.getir.books;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.getir.books.controller.BookController;
import com.getir.books.controller.CustomerController;
import com.getir.books.controller.OrderController;
import com.getir.books.entity.Book;
import com.getir.books.entity.Customer;
import com.getir.books.exceptions.BusinessException;
import com.getir.books.model.ApiResponse;
import com.getir.books.model.BookRequest;
import com.getir.books.model.CustomerRequest;
import com.getir.books.model.DateRequest;
import com.getir.books.model.OrderModel;
import com.getir.books.model.OrderRequest;
import com.getir.books.repository.BookRepository;
import com.getir.books.repository.CustomerRepository;

@SpringBootTest(classes = BooksApplication.class)
@ActiveProfiles(profiles = "test")
class ControllerTest {

	@Autowired
	private CustomerController customerController;

	@Autowired
	private BookController bookController;

	@Autowired
	private OrderController orderController;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookRepository bookRepository;
	

	private CustomerRequest customerRequest = new CustomerRequest();
	private BookRequest bookRequest = new BookRequest();
	private OrderRequest orderRequest = new OrderRequest();

	@BeforeEach
	public void setup() {
		customerRequest.setAddress("sdfsdf");
		customerRequest.setCustomerName("test_user");
		customerRequest.setCustomerSurname("test");

		bookRequest.setBookName("Umut");
		bookRequest.setBookCount(5);
		bookRequest.setBookPrice(new BigDecimal(55.15));

	}

	@Test
	void createCustomerTest() {
		Customer customer = customerRepository.findByEmail("test3@test.com");
		customerRepository.delete(customer);
		customerRequest.setEmail("test3@test.com");
		ResponseEntity<ApiResponse> response = customerController.createCustomer(customerRequest);
		HttpStatus expectedValue = HttpStatus.OK;
		HttpStatus actaulValue = response.getStatusCode();
		assertEquals(expectedValue, actaulValue);
	}

	@Test
	void customerValidationTest1() {
		Exception exception = assertThrows(BusinessException.class, () -> {
			customerRequest.setEmail("");
			ResponseEntity<ApiResponse> response = customerController.createCustomer(customerRequest);
		});

		String expectedMessage = "Customer email can not be null";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void customerValidationTest2() {
		Exception exception = assertThrows(BusinessException.class, () -> {
			customerRequest.setCustomerName("");
			ResponseEntity<ApiResponse> response = customerController.createCustomer(customerRequest);
		});

		String expectedMessage = "Customer name can not be null";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void updateCustomerTest() {
		customerRequest.setEmail("test3@test.com");
		customerRequest.setAddress("Çukurova");
		ResponseEntity<ApiResponse> response = customerController.updateCustomer(customerRequest);
		HttpStatus expectedValue = HttpStatus.OK;
		HttpStatus actaulValue = response.getStatusCode();
		assertEquals(expectedValue, actaulValue);
	}

	@Test
	void createBookTest() {
		Book book = bookRepository.findByBookName("Umut");
		bookRepository.delete(book);
		ResponseEntity<ApiResponse> response = bookController.createBook(bookRequest);
		HttpStatus expectedValue = HttpStatus.OK;
		HttpStatus actaulValue = response.getStatusCode();
		assertEquals(expectedValue, actaulValue);
	}

	@Test
	void bookValidationTest() {
		Exception exception = assertThrows(BusinessException.class, () -> {
			bookRequest.setBookName("");
			ResponseEntity<ApiResponse> response = bookController.createBook(bookRequest);
		});

		String expectedMessage = "Book name can not be null";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void bookValidationTest2() {
		Exception exception = assertThrows(BusinessException.class, () -> {
			bookRequest.setBookName("sdfsdf");
			bookRequest.setBookCount(0);
			ResponseEntity<ApiResponse> response = bookController.createBook(bookRequest);
		});

		String expectedMessage = "Please enter a book count more than zero";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void updateBookTest() {
		bookRequest.setBookCount(15);
		ResponseEntity<ApiResponse> response = bookController.updateBook(bookRequest);
		HttpStatus expectedValue = HttpStatus.OK;
		HttpStatus actaulValue = response.getStatusCode();
		assertEquals(expectedValue, actaulValue);
	}

	//@Test
	void createOrderTest() {
		orderRequest.setCustomerId(1L);
		OrderModel orderModel = new OrderModel();
		orderModel.setBookId(1L);
		orderModel.setBookCount(2);
		orderModel.setStartDate(new Date());
		orderModel.setEndDate(new Date());
		orderModel.setOrderName("Test Order");
		List<OrderModel> orderList = new ArrayList<>();
		orderList.add(orderModel);
		orderRequest.setOrderList(orderList);
		ResponseEntity<ApiResponse> response = orderController.createOrder(orderRequest);
		HttpStatus expectedValue = HttpStatus.OK;
		HttpStatus actaulValue = response.getStatusCode();
		assertEquals(expectedValue, actaulValue);
	}
	
	@Test
	void validateOrderTest() {
		Exception exception = assertThrows(BusinessException.class, () -> {
			orderRequest.setCustomerId(1L);
			OrderModel orderModel = new OrderModel();
			orderModel.setBookCount(2);
			orderModel.setStartDate(new Date());
			orderModel.setEndDate(new Date());
			orderModel.setOrderName("Test Order");
			List<OrderModel> orderList = new ArrayList<>();
			orderList.add(orderModel);
			orderRequest.setOrderList(orderList);
			ResponseEntity<ApiResponse> response = orderController.createOrder(orderRequest);
		});

		String expectedMessage = "Book id can not be null";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void validateOrderTest2() {
		Exception exception = assertThrows(BusinessException.class, () -> {
			OrderModel orderModel = new OrderModel();
			orderModel.setBookId(1L);
			orderModel.setBookCount(2);
			orderModel.setStartDate(new Date());
			orderModel.setEndDate(new Date());
			orderModel.setOrderName("Test Order");
			List<OrderModel> orderList = new ArrayList<>();
			orderList.add(orderModel);
			orderRequest.setOrderList(orderList);
			ResponseEntity<ApiResponse> response = orderController.createOrder(orderRequest);
		});

		String expectedMessage = "Customer id can not be null";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void getOrderByIdTest() {
		Exception exception = assertThrows(BusinessException.class, () -> {
			ResponseEntity<ApiResponse> response = orderController.getOrderById(1L);
		});

		String expectedMessage = "Order Cannot found.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
		
	}
	
	@Test
	void getOrderByDatesTest() {
		DateRequest dateRequest = new DateRequest();
		dateRequest.setStartDate(new Date());
		dateRequest.setEndDate(new Date());
		Exception exception = assertThrows(BusinessException.class, () -> {
			ResponseEntity<ApiResponse> response = orderController.getOrdersByDate(dateRequest);
		});

		String expectedMessage = "OrderList can not found";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
		
	}

}
