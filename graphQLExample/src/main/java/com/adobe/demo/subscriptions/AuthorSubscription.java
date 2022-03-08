package com.adobe.demo.subscriptions;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adobe.demo.entity.Author;

import graphql.kickstart.tools.GraphQLSubscriptionResolver;

@Component
public class AuthorSubscription implements GraphQLSubscriptionResolver {
	
	@Autowired
	private AuthorPublisher publisher;
	
	public Publisher<Author> authors() {
		return publisher.getPublisher();
	}
}
