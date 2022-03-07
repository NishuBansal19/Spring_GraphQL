package com.adobe.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adobe.demo.entity.Author;

public interface AuthorDao extends JpaRepository<Author, Integer> {

}
