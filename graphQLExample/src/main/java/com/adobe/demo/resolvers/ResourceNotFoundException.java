package com.adobe.demo.resolvers;

import java.util.List;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class ResourceNotFoundException extends RuntimeException implements GraphQLError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorClassification getErrorType() {
		return ErrorType.DataFetchingException;
	}

}
