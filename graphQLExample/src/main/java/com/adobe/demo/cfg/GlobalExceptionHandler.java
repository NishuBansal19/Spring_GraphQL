package com.adobe.demo.cfg;

import javax.validation.ConstraintViolationException;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;

@Component
public class GlobalExceptionHandler {
	
	@ExceptionHandler({GraphQLException.class, ConstraintViolationException.class})
	public ThrowableGraphQLError handle(Exception ex) {
		System.out.println("---------> " +  ex);
		return new ThrowableGraphQLError(ex);
	}
}
