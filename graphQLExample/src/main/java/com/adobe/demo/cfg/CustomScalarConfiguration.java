package com.adobe.demo.cfg;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adobe.demo.entity.Mobile;
import com.adobe.demo.entity.Product;
import com.adobe.demo.entity.Tv;

import graphql.kickstart.tools.SchemaParserDictionary;
import graphql.kickstart.tools.boot.SchemaDirective;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

@Configuration
public class CustomScalarConfiguration {
	@Bean
	public SchemaParserDictionary getSchemaParserDictionary() {
		return new SchemaParserDictionary().add(Mobile.class).add(Tv.class).add(Product.class);
	}
	
	@Bean
	public SchemaDirective uppercaseSchemaDirective() {
		return new SchemaDirective("uppercase", new UppercaseDirective());
	}
	
	@Bean
	public GraphQLScalarType dateScalar() {
		return GraphQLScalarType.newScalar()
				.name("Date")
				.description("Custom Date Scalar type")
				.coercing(new Coercing<Date, String>() {

					@Override
					public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
						return dataFetcherResult.toString();
					}

					@Override
					public Date parseValue(Object input) throws CoercingParseValueException {
						try {
							return DateFormat.getInstance().parse((String)input);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						return null;
					}

					@Override
					public Date parseLiteral(Object input) throws CoercingParseLiteralException {
						try {
							return DateFormat.getInstance().parse((String)input);
						} catch (ParseException e) {
							e.printStackTrace();
						}
						return null;
					}
					
				}).build();
				
	}
}
