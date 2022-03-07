package com.adobe.demo.resolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adobe.demo.dao.BookDao;
import com.adobe.demo.entity.Book;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class BookQueryResolver implements GraphQLQueryResolver {
	@Autowired
	private BookDao bookDao;
	
	//public List<Book> books() {
	public List<Book> getBooks() {
		return bookDao.findAll();
	}
}
