package com.adobe.demo.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adobe.demo.dao.PublisherDao;
import com.adobe.demo.entity.Book;
import com.adobe.demo.entity.Publisher;

import graphql.kickstart.tools.GraphQLResolver;

@Component
public class BookFieldQueryResolver implements GraphQLResolver<Book>{
	 @Autowired
	 private PublisherDao publisherDao;
	 
	 public Publisher getPublisher(Book book) {
		 return publisherDao.findById(book.getPublisherId()).get();
	 }
	 
	 public int getVersion(Book book) {
		 return 9;
	 }
}
