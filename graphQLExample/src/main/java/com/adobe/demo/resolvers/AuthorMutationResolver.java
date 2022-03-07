package com.adobe.demo.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.adobe.demo.dao.AuthorDao;
import com.adobe.demo.entity.Author;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
public class AuthorMutationResolver implements GraphQLMutationResolver {
	@Autowired
	private AuthorDao authorDao;
	
	public Integer createAuthor(Author author) {
		return authorDao.save(author).getId();
	}
}
