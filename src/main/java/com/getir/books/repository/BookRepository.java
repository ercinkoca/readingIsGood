package com.getir.books.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.getir.books.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query("Select b from Book b where b.bookName = :bookName")
	Book findByBookName(@Param("bookName") String bookName);
	
	@Query("Select b from Book b where b.bookId = :bookId")
	Book findByBookId(@Param("bookId") Long bookId);
}
