package com.adobe.demo.resolvers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adobe.demo.dao.BookDao;
import com.adobe.demo.entity.Book;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookQueryResolver implements GraphQLQueryResolver {
	@Autowired
	private BookDao bookDao;
	
	//public List<Book> books() {
	public List<Book> getBooks(DataFetchingEnvironment env) {
		Set<String> fields = 
				env.getSelectionSet().getFields().stream().map(field -> field.getName())
				.collect(Collectors.toSet());
		
		System.out.println(fields);
		return bookDao.findAll();
	}
	
	public Book getBookById(int id) {
		return bookDao.findById(id).get();
	}
}
