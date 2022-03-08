package com.adobe.demo.resolvers;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.adobe.demo.dao.BookDao;
import com.adobe.demo.entity.Book;

import graphql.execution.DataFetcherResult;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.relay.Connection;
import graphql.relay.SimpleListConnection;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookQueryResolver implements GraphQLQueryResolver {
	@Autowired
	private BookDao bookDao;
	
	public Connection<Book> booksByPage(int first, String after, DataFetchingEnvironment env) {
		return new SimpleListConnection<>(bookDao.findAll()).get(env);
	}
	//public List<Book> books() {
	public List<Book> getBooks(DataFetchingEnvironment env) {
		Set<String> fields = 
				env.getSelectionSet().getFields().stream().map(field -> field.getName())
				.collect(Collectors.toSet());
		
		System.out.println(fields);
		return bookDao.findAll();
	}
	
	public Book getBookById(int id) {
		Optional<Book> opt = bookDao.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		} else {
			throw new ResourceNotFoundException("Book with " + id + " doesn't exist");
		}
	}
	
	public DataFetcherResult<Book> partialBookById(int id) {
		return DataFetcherResult.<Book>newResult()
				.data(bookDao.findById(id).get())
				.error(new GenericGraphQLError("couldn't get publisher for book " + id))
				.build();
	}
}
