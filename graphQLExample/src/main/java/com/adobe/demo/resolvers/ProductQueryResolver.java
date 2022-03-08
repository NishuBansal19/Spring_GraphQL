package com.adobe.demo.resolvers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.adobe.demo.entity.Mobile;
import com.adobe.demo.entity.Product;
import com.adobe.demo.entity.Tv;

import graphql.kickstart.tools.GraphQLQueryResolver;

@Component
public class ProductQueryResolver implements GraphQLQueryResolver {
	public List<Product> getProducts() {
		Product[] products = new Product[5]; // Array of 5 Pointers
		products[0] = new Tv(133, "Sony Bravia", 135000.00, "LED"); // upcasting
		products[1] = new Mobile(453, "MotoG", 12999.00, "4G");
		products[2] = new Tv(634, "Onida Thunder", 3500.00, "CRT");
		products[3] = new Mobile(621, "iPhone XR", 99999.00, "4G");
		products[4] = new Mobile(844, "Oppo", 9999.00, "4G");
		
		return Arrays.asList(products);
	}
}
