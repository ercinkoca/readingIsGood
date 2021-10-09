package com.getir.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.getir.books.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	@Query("Select cs from Customer cs where cs.email = :email")
	Customer findByEmail(@Param("email") String email);
	
	@Query("Select cs from Customer cs where cs.customerId = :customerId")
	Customer findByCustomerId(@Param("customerId") Long customerId);

}
