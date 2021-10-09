package com.getir.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.getir.books.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("Select o from Order o where o.orderId = :orderId")
	Order findByOrderId(@Param("orderId") Long orderId);

	@Query("Select o from Order o where o.startDate = :startDate and o.endDate = :endDate")
	List<Order> findByDates(@Param("startDate") String startDate, @Param("endDate") String endDate);

	@Query("Select o from Order o where o.customer.customerId = :customerId")
	List<Order> findByCustomerId(@Param("customerId") Long customerId);

}
