package com.adobe.demo.resolvers;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class HelloWorldQueryResolver implements GraphQLQueryResolver {
//	public String getHelloWorld() { }
	
	public String helloWorld() {
		return "Hello World GraphQL!!";
	}
	
	public String greeting(String firstName, String lastName) {
		return String.format("Hello %s %s", firstName, lastName);
	}
}
