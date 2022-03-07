package com.adobe.demo;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.adobe.demo.dao.BookDao;
import com.adobe.demo.entity.Book;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes= TestApplication.class)
public class BookGraphQLTest {
	@Autowired
	private GraphQLTestTemplate template;
	
	@MockBean
	private BookDao bookDao;
	
	@Test
	public void getBook() throws Exception {
		String expected = "{\"data\":{\"bookById\":{\"title\":\"Some title\",\"rating\":3.87}}}";
		
		Book book = new Book();
		book.setTitle("Some title");
		book.setRating(3.87);
		
		Optional<Book> opt = Optional.of(book);
		// mocking
		when(bookDao.findById(1)).thenReturn(opt);
		
		GraphQLResponse response = template.postForResource("get-book.graphqls");
		
		System.out.println(response);
		
		assertThat(response.isOk(), equalTo(true));
		assertEquals(expected, response.getRawResponse().getBody());
				
	}
}
