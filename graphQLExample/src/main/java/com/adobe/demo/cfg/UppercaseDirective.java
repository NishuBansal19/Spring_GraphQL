package com.adobe.demo.cfg;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetcherFactories;
import graphql.schema.FieldCoordinates;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLFieldsContainer;
import graphql.schema.idl.SchemaDirectiveWiring;
import graphql.schema.idl.SchemaDirectiveWiringEnvironment;

public class UppercaseDirective implements SchemaDirectiveWiring {
	
	@Override
	public GraphQLFieldDefinition onField(SchemaDirectiveWiringEnvironment<GraphQLFieldDefinition> environment) {
		GraphQLFieldDefinition fieldDefinition = environment.getFieldDefinition();
		GraphQLFieldsContainer parentType = environment.getFieldsContainer();
		DataFetcher original = environment.getCodeRegistry().getDataFetcher(parentType, fieldDefinition);
				
		DataFetcher dataFetcher = DataFetcherFactories.wrapDataFetcher(original, ((env, value) -> {
				if(value != null) {
					return ((String)value).toUpperCase();
				}
				return value;
		}));
		
		FieldCoordinates coords = FieldCoordinates.coordinates(parentType, fieldDefinition);
		environment.getCodeRegistry().dataFetcher(coords, dataFetcher);
        return environment.getElement();
    }
}
