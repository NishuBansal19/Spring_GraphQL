package com.adobe.demo.resolvers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.adobe.demo.dao.AuthorDao;
import com.adobe.demo.entity.Author;

import graphql.kickstart.tools.GraphQLMutationResolver;

@Component
@Validated
public class AuthorMutationResolver implements GraphQLMutationResolver {
	@Autowired
	private AuthorDao authorDao;
	
	public Integer createAuthor(@Valid Author author) {
		return authorDao.save(author).getId();
	}
}
