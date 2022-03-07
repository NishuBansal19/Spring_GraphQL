package com.adobe.demo.resolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adobe.demo.dao.PublisherDao;
import com.adobe.demo.entity.Publisher;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class PublisherQueryResolver implements GraphQLQueryResolver {
	@Autowired
	private PublisherDao publisherDao;
	
	public List<Publisher> getPublishers() {
		return publisherDao.findAll();
	}
}
