package com.adobe.demo.cfg;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;

@Component
public class SimpleGraphQLErrorHandler implements GraphQLErrorHandler {

	@Override
	public List<GraphQLError> processErrors(List<GraphQLError> errors) {
		return errors.stream().map(exp -> {
			if(exp instanceof ExceptionWhileDataFetching) {
				ExceptionWhileDataFetching ex  = (ExceptionWhileDataFetching) exp;
				return (GraphQLError) ex.getException();
			}
			return exp;
		}).collect(Collectors.toList());
		
	}
	
}
