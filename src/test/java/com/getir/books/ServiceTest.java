package com.getir.books;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.getir.books.entity.Book;
import com.getir.books.entity.Customer;
import com.getir.books.entity.Order;
import com.getir.books.exceptions.BusinessException;
import com.getir.books.model.ApiResponse;
import com.getir.books.model.BookRequest;
import com.getir.books.model.CustomerRequest;
import com.getir.books.model.OrderModel;
import com.getir.books.model.OrderRequest;
import com.getir.books.model.Statistics;
import com.getir.books.model.StatisticsResponse;
import com.getir.books.repository.BookRepository;
import com.getir.books.repository.CustomerRepository;
import com.getir.books.repository.OrderRepository;
import com.getir.books.service.impl.BookServiceImpl;
import com.getir.books.service.impl.CustomerServiceImpl;
import com.getir.books.service.impl.OrderServiceImpl;
import com.getir.books.service.impl.StatisticsServiceImpl;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK,classes = BooksApplication.class)
@ActiveProfiles(profiles = "test")
class ServiceTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private CustomerServiceImpl customerService;
	
	@Autowired
	private BookServiceImpl bookService;

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private StatisticsServiceImpl statisticsService;
	
	@MockBean
	private OrderServiceImpl orderService;
	
	@MockBean
	private OrderRepository orderRepository;

	private CustomerRequest customerRequest = new CustomerRequest();
	
	private BookRequest bookRequest = new BookRequest();
	
	private OrderRequest orderRequest = new OrderRequest();
	
	@BeforeEach
	void setUp() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void createCustomerTest() {
		Customer customer = customerRepository.findByEmail("kocaercin@gmail.com");
		customerRepository.delete(customer);

		customerRequest.setAddress("Adana");
		customerRequest.setCustomerName("erçin");
		customerRequest.setCustomerSurname("koca");
		customerRequest.setEmail("kocaercin@gmail.com");

		ApiResponse response = customerService.create(customerRequest);
		assertEquals(200, response.getCode());
	}

	@Test
	void updateCustomerTest() {
		customerRequest.setAddress("İstanbul");
		customerRequest.setCustomerName("erçin");
		customerRequest.setCustomerSurname("koca");
		customerRequest.setEmail("kocaercin@gmail.com");

		ApiResponse response = customerService.update(customerRequest);
		assertEquals(200, response.getCode());
	}
	
	@Test
	void getAllOrders() {
		Exception exception = assertThrows(BusinessException.class, () -> {
			ApiResponse response = customerService.getAllOrders(2L);
		});

		String expectedMessage = "Customer can not found";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void emailCheck() {
		List<Customer> customerList = customerRepository.findAll();
		Customer customer = customerList.parallelStream().filter(x -> x.getEmail().equals("kocaercin@gmail.com"))
				.findFirst().orElse(null);
		assertNotNull(customer);
	}
	
	@Test
	void createBook() {
		Book book = bookRepository.findByBookName("Fareler ve İnsanlar");
		bookRepository.delete(book);
		
		bookRequest.setBookCount(5);
		bookRequest.setBookName("Fareler ve İnsanlar");
		bookRequest.setBookPrice(new BigDecimal(35.99));
		
		ApiResponse response = bookService.create(bookRequest);
		assertEquals(200, response.getCode());
	}
	
	@Test
	void updateBook() {
		bookRequest.setBookName("Fareler ve İnsanlar");
		bookRequest.setBookCount(30);
		bookRequest.setBookPrice(new BigDecimal(24.99));
		
		ApiResponse response = bookService.update(bookRequest);
		assertEquals(200, response.getCode());
	}
	
	@Test
	void createOrder() {
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
		ApiResponse response = new ApiResponse(200, "Order Created", null);
		given(orderService.create(orderRequest)).willReturn(response);
		assertEquals(200, response.getCode());
	}
	
	@Test
	void getOrderById() {
		OrderModel orderModel = new OrderModel();
		orderModel.setBookCount(2);
		orderModel.setBookId(1L);
		orderModel.setEndDate(new Date());
		orderModel.setStartDate(new Date());
		orderModel.setOrderName("test_order");
		ApiResponse response = new ApiResponse(200, "Success", orderModel);
		given(orderService.getOrderById(1L)).willReturn(response);
		assertEquals(200, response.getCode());
	}
	
	@Test
	void bookControl() {
		String bookName = "Fareler ve İnsanlar";
		int orderBookCount = 1;
		Book book = bookRepository.findByBookName(bookName);
		assertTrue(book.getBookCount() - orderBookCount >=0);
	}
	
	@Test
	void statisticsTest() {
		Book book = bookRepository.findByBookName("Fareler ve İnsanlar");
		Customer customer = customerRepository.findByEmail("kocaercin@gmail.com");
		Order order = new Order();
		order.setBook(book);
		order.setCustomer(customer);
		order.setEndDate(new Date());
		order.setStartDate(new Date());
		order.setOrderName("test_order");
		order.setTotalAmount(new BigDecimal(55.99));
		order.setOrderMonth();
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order);
		given(orderRepository.findByCustomerId(1L)).willReturn(orderList);
		ApiResponse response = statisticsService.getCustomerStatistic(1L);
		assertEquals(200, response.getCode());
	}
	
	@Test
	void statisticResponseTest() {
		Book book = bookRepository.findByBookName("Fareler ve İnsanlar");
		Customer customer = customerRepository.findByEmail("kocaercin@gmail.com");
		Order order = new Order();
		order.setBook(book);
		order.setCustomer(customer);
		order.setEndDate(new Date());
		order.setStartDate(new Date());
		order.setOrderName("test_order");
		order.setTotalAmount(new BigDecimal(56));
		order.setOrderMonth();
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order);
		given(orderRepository.findByCustomerId(1L)).willReturn(orderList);
		ApiResponse response = statisticsService.getCustomerStatistic(1L); //bdTest.setScale(2, BigDecimal.ROUND_HALF_UP)
		
		StatisticsResponse statResponse = (StatisticsResponse) response.getData();
		assertEquals("October", statResponse.getStatisticList().get(0).getMonth());
		assertEquals(new BigDecimal(56), statResponse.getStatisticList().get(0).getTotalPurchasedAmount());
		
	}
	
	@Test
	void calculateDataTest() {
		Book book = bookRepository.findByBookName("Fareler ve İnsanlar");
		Customer customer = customerRepository.findByEmail("kocaercin@gmail.com");
		Order order = new Order();
		order.setBook(book);
		order.setCustomer(customer);
		order.setEndDate(new Date());
		order.setStartDate(new Date());
		order.setOrderName("test_order");
		order.setTotalAmount(new BigDecimal(56));
		order.setOrderMonth();
		List<Order> orderList = new ArrayList<Order>();
		orderList.add(order);
		Statistics statisticData = statisticsService.calculateData(orderList, 10);
		assertEquals("October", statisticData.getMonth());
	}

}
