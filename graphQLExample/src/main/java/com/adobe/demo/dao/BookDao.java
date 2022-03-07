package com.adobe.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adobe.demo.entity.Book;

public interface BookDao extends JpaRepository<Book, Integer> {
	@Query("from Book where publisherId = :pubId")
	List<Book> getBooksByPublisherId(@Param("pubId") int id);
}
