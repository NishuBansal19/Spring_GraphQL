package com.adobe.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adobe.demo.entity.Book;

public interface BookDao extends JpaRepository<Book, Integer> {

}
