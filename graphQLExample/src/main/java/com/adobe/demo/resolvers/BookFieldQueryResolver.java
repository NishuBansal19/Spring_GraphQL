package com.adobe.demo.resolvers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adobe.demo.dao.PublisherDao;
import com.adobe.demo.entity.Book;
import com.adobe.demo.entity.Publisher;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class BookFieldQueryResolver implements GraphQLResolver<Book> {
	@Autowired
	private PublisherDao publisherDao;
//	 
//	 public Publisher getPublisher(Book book) {
//		 return publisherDao.findById(book.getPublisherId()).get();
//	 }

	private final ExecutorService service = Executors.newFixedThreadPool(2);

	public CompletableFuture<Publisher> getPublisher(Book book) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				log.info("Publisher request started {}", book.getId());
				Publisher publisher = publisherDao.findById(book.getPublisherId()).get();
				log.info("Publihser request completed {}", book.getId());
				return publisher;
			} catch (Exception e) {
				return null;
			}
		}, service);
	}

	public int getVersion(Book book) {
		return 9;
	}
}
