# Spring_GraphQL
GraphQL - Spring Boot

Banuprakash C

Full Stack Architect,

Co-founder Lucida Technologies Pvt Ltd.,

Corporate Trainer,

Email: banuprakashc@yahoo.co.in

https://www.linkedin.com/in/banu-prakash-50416019/

https://github.com/BanuPrakash/Spring_GraphQL

https://notepad.ltd/egrnhgxh

------------
Softwares Required:
1) Java 8+

2) Eclipse IDE for Enterprise Java Developers: 
	https://www.eclipse.org/downloads/packages/release/2020-03/m1/eclipse-ide-enterprise-java-developers

3) Eclipse Plugin STS [Spring Tools 4 (aka Spring Tool Suite 4)]

-----------

Java, Spring Boot and Spring Data JPA

Maven

GraphQL integration with Spring Boot
* Schema
* Resolvers
* FieldResolvers
* Validation
* Mutation
* Subscription
* DataLoaders
* Secure --> JWT

--------------------------------------------------------------



RESTful ==> REPRESENTAIONAL STATE TRANSFER ==> Architectural pattern using HTTP

PLURAL NOUNS ==> identify resources

http://server/api/products
http://server/api/products/2
http://server/api/products?category=mobile
http://server/api/orders/banu@gmail.com

HTTP Methods ==> GET, POST, PUT, DELETE ==> CRUD operations

GraphQL
==> by Facebook ==> 2012

* Query Language to query data from any number of different sources
* Server side runtime ==> served over HTTP
* Architectural Pattern

Why GraphQL?
* No over-fetching
http://jsonplaceholder.typicode.com/users

IN RESTful ==> http://jsonplaceholder.typicode.com/users?fields=username,email

* No Under-fetching
https://jsonplaceholder.typicode.com/posts
gives me 100 posts

need to get comments for the posts
https://jsonplaceholder.typicode.com/comments?postId=1
https://jsonplaceholder.typicode.com/comments?postId=2
https://jsonplaceholder.typicode.com/comments?postId=3
...
https://jsonplaceholder.typicode.com/comments?postId=100

n + 1 hits to Server

* No stitching of multiple responses
	with GraphQL we can ask related data, keeping queries cohesive, and spend zero time stitching

* Single Source of truth

URL: http://server.com/graphql
METHOD: POST

* Strongly typed schema
GraphQL Schema acts as contract; can be shared with client, client can validate, can mock,...

---------------------------------------------

GraphQL Schema (SDL)
* schema is a description of the data clients can request from a GraphQL API

schema {
	query:Query
	mutation: Mutation
	subscription: Subscription
}

==> GET, fetching data
type Query {

}

==> CREATE, UPDATE, DELETE
type Mutation {

}

==> Observer, Observable pattern
==> client subscribes for any changes on a subject. Wheenever data changes on Subject : person gets notification
type Subscription {

}


Schema Types:
1) Scalar
2) Object
3) Enum
4) Input
5) Union
6) Interface


Scalar types:
1) String --> GraphQLString == > UTF 8
2) Boolean --> GraphQLBoolean ==> true / false
3) Int --> GraphQLInt --> 32 bit unsigned integer
4) Float --> GraphQLFloat --> double prescision value
5) ID --> GraphQLID --> UUID

-----------------

GraphQL library
GraphQL-kickstart Spring boot integration library


--------------------------------------------

https://howtodoinjava.com/lombok/lombok-eclipse-installation-examples/


=============

GraphQL-kickstart-springboot scans of files with extenstion of ".graphql" or ".graphqls" in classpath and schema is parsed

schema {
	query: Query
}


type Query {
	helloWorld:String!
}

! --> Not Null




http://localhost:8080/voyager

http://localhost:8080/graphiql
http://localhost:8080/playground

REQUEST:
query {
  helloWorld
}

RESPONSE:
{
  "data": {
    "helloWorld": "Hello World GraphQL!!"
  }
}

--

query {
  greeting(firstName:"Banu", lastName:"Prakash")
}

{
  "data": {
    "greeting": "Hello Banu Prakash"
  }
}

========
schema.graphqls

type Query {
 	books: [Book]
}

# book object type
type Book {
	id:Int,
	title:String!,
	totalPages:Int,
	rating:Float,
	isbn:String
}


Book.java
BookDao.java
BookQueryResolver.java  

query {
  books {
    title,
    rating,
    isbn
  }
}

===============

JPA ==> JPASpecification, EntityGraph, @Query

=============================
Query Arguments:

query {
  bookById(id:3) {
    title
    rating
  }
}

====

Using Query Variables:

Query:

query GET_BOOK_BY_ID($bid:Int) {
  bookById(id:$bid) {
    title
    rating
  }
}

Query Variable:

{
  "bid": 2
}

=================

unit testing RESTful Web services ==> we test @RestController ==> Mock Service

Unit Testing GraphQL [Resolvers] ==> Mock Services

EasyMock, JMock and Mockito [ spring boot comes builtin]

json-path

====================================

schema files ==> .graphql or .graphqls ==> classpath ==> TypeDefintionRegistry ==. GraphQLSchema

RuntimeWiring ==> QueryResolvers [ implements GraphQLQueryResolver ==> @Component]

--

http://localhost:8080/graphiql
http://localhost:8080/playground
http://localhost:8080/voyager


==================================

Book might have computed values, or any data which is not a part of database or associated class [Publisher].


public class Book {
	@Id
	@Column(name="book_id")
	private int id;
	private String title;
	@Column(name="total_pages")
	private Integer totalPages;
	private double rating;
	private String isbn;
	@Transient
	private int version;
}


class BookFieldQueryResolver implements GraphQLResolver<Book> {

	public String getTitle() {
		return "Dummy Book";
	}

	public double getRating() {
		return 4.9;
	}

	public int getVersion() {
		return 1;
	}
}


====

Association Mapping between Book and Publisher [ ManyToOne]



query {
  books {
    title 
    version
    publisher {
      id
      name
    }
  }
}

==============

Publisher has Many books [ OneToMany ]

query {
  publishers {
    id
    name
    books {
      title
      rating
      publisher {
        name
      }
    }
  }
}

==========

Custom Scalar Type
class Book {
	@Column(name="published_date")
	private Date publishedDate;


--

schema.grapqls

scalar Date

type Book {
	...
	publishedDate:Date,
}

--

Date in backend has to be searialized as String to client

From client String data passed has to be converted to Date


package com.adobe.demo.cfg;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;

@Configuration
public class CustomScalarConfiguration {
	
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


==========

query {
     books {
      title
      rating
      publishedDate
    } 
}

=============================================================

https://github.com/graphql-java/graphql-java-extended-scalars

===========================================================
 I parseLiteral(Object input) throws CoercingParseLiteralException;

query {
	booksByPublishedDate("publishedDate": "2002-10-30") {
		title
	}
}


---
I parseValue(Object input) throws CoercingParseValueException;


query {
	booksByPublishedDate("publishedDate": $pdata) {
		title
	}
}

Query Variable:
{
	"pdate": "2002-10-30"
}

---------------------

POSTMAN as GraphQL Client:

POST http://localhost:8080/graphql

Body ==> GraphQL

Query:
query GET_BY_ID($bid:Int) {
    bookById(id:$bid) {
        title
    }
}

GraphQL Variables:
{
    "bid": 3
}

curl --location --request POST 'http://localhost:8080/graphql' \
--header 'Content-Type: application/json' \
--data-raw '{"query":"query GET_BY_ID($bid:Int) {\r\n    bookById(id:$bid) {\r\n        title\r\n    }\r\n}","variables":{"bid":3}}'

============================================================

* Directives

Built in Directives:
1) @deprecated
2) @skip
3) @include
4) @specifiedBy

query ($admin: Boolean!) {
    books {
      title
      rating
      publisher @include(if: $admin) {
        name
      }
    } 
}

Query Variables:
{
  "admin": false
}

===

Custom Directives

=========================================

Spring
1) @Component
2) @Respository
3) @Controller
4) @RestController
5) @Configuration
6) @Service

classes with "package com.adobe.demo" and subpackages and having any of above annotations ==> Spring creates instances of those classes

In configaration classes methods with @Bean are treated as factory methods ==> invoked and returned objects from
these methods are managed by spring container

====================================================

GraphQLError handling

query {
  bookById(id:9999) {
    title
  }
}

{
  "errors": [
    {
      "message": "Internal Server Error(s) while executing query",
      "locations": []
    }
  ],
  "data": {
    "bookById": null
  }
}

public class ResourceNotFoundException extends RuntimeException implements GraphQLError {


SimpleDataFetcherExceptionHandler
private String mkMessage(ResultPath path, Throwable exception) {
        return format("Exception while fetching data (%s) : %s", path, exception.getMessage());
    }

"message": "Exception while fetching data (/bookById) : Book with 9999 doesn't exist",

public class SimpleGraphQLErrorHandler implements GraphQLErrorHandler {

}

=============

@ControllerAdvice
class GlobalExceptionHandler {

	@Exception(ResourcenotFoundException.class) 
	m1() {

	}

	@Exception(ResourcenotFoundException.class) 
	m1() {

	}

}

==========================

Mutation

schema {
	query: Query
	mutation: Mutation
	subscription:Subscription
}


type Mutation {
 createAutor(firstName:String!, lastName:String, middleName:String): Int
}

----

type Mutation {
 createAuthor(author:AuthorInput): Int
}

# DTO
input AuthorInput {
	firstName:String!,
	lastName:String,
	middleName:String
}


@Entity
@Table(name = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "author_id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	@Column(name = "middle_name")
	private String middleName;
}

public interface AuthorDao extends JpaRepository<Author, Integer> {

}


@Component
public class AuthorMutationResolver implements GraphQLMutationResolver {
	@Autowired
	private AuthorDao authorDao;
	
	public Integer createAuthor(Author author) {
		return authorDao.save(author).getId();
	}
}


============

Validation:

Author:
	@NotBlank(message = "First Name is required")
	@Column(name = "first_name")
	private String firstName;

---

@Component
@Validated
public class AuthorMutationResolver implements GraphQLMutationResolver {
	@Autowired
	private AuthorDao authorDao;
	
	public Integer createAuthor(@Valid Author author) {
		return authorDao.save(author).getId();
	}
}

================
* GraphQLQueryResolver ==> handling query 
* GraphQLResolver<T> ==> field resolvers
* GraphQLMutationResolver


Schema ==> Scalar, Object type, custom scalar, directives

=============================

* Async Opertions
* Pagination --> Relay Specification
* Security

=================



Recap:
1) GraphQL SDL

schema {
	query: Query
	mutation: Mutation
	subscription: Subscription
}

type Query {

}

type Mutation {

}

type Subscription {

}

2) Scalar, CustomScalar --> Coercing interface

3) GraphQLQueryResolver for fields present in type Query
4) GraphQLResolver<T> for resolving computed field, association of a type ==> Book, Publisher
5) Directives
6) GraphQLError
7) @Validate and @Valid using input validations ==> javax.validation.contraints ==> @NotNull, @Min, @Max
8) DataFetchingEnvironment

----------------------------

Day 2

* Sync Operations


@Component
public class BookFieldQueryResolver implements GraphQLResolver<Book>{
	 @Autowired
	 private PublisherDao publisherDao;
	 
	 @Autowired
	 private AuthorDao authorDao;

	 @Autowired
	 private SupplierDao supplierDao;

	 public Publisher getPublisher(Book book) {
		 return publisherDao.findById(book.getPublisherId()).get();
	 }
	 
	  public List<Author> getAuthors(Book book) {
		 ..
	 }

	  public Supplier getSupplier(Book book) {
		 	// ..
	 }
}


* Async Operations


@Component
public class BookFieldQueryResolver implements GraphQLResolver<Book>{
	 @Autowired
	 private PublisherDao publisherDao;
	 
	 @Autowired
	 private AuthorDao authorDao;

	 @Autowired
	 private SupplierDao supplierDao;

	 public CompletableFuture<Publisher> getPublisher(Book book) {
		 
	 }
	 
	  public CompletableFuture<List<Author>> getAuthors(Book book) {
		 ..
	 }

	  public CompletableFuture<Supplier> getSupplier(Book book) {
		 	// ..
	 }
}

====================

Future is a placeholder for returned Promise

Promise ==> pending, fullfilled / rejected

CompletableFuture ==> Future + CompletionStage \


query {
   books {
    title 
    publisher {
      name
    }
  }
}

===================================================================

Returing Partial Data ==> DataFetcherResult

======================

Pagination

Offset based:

public Page<Book> paginate(int page, int size) {
	return bookDao.findAll(PageRequest.of(page,size)); // JPA pagination
}

----------------------------------------
https://relay.dev/graphql/connections.htm

Relay ==> Facebook ==> Connection for Pagination

{
  user {
    id
    name
    friends(first: 10, after: "45dvs23vsdf") {
      edges {
        cursor
        node {
          id
          name
        }
      }
      pageInfo {
        hasNextPage
        hasPreviousPage
      }
    }
  }
}

 


---

 pageInfo {
        hasNextPage
        hasPreviousPage
  }


---

 edges {
        cursor
        node {
          id
          name
        }
      }

===========================================

booksByPage(first:Int, after:String): BookConnection

Query:

query {
  booksByPage(first:10) {
    edges {
      cursor
      node {
        title
        rating
      }
    }
    pageInfo {
      hasNextPage
      hasPreviousPage
    }
  }
}

---

booksByPage(first:2, after:"c2ltcGxlLWN1cnNvcjI") {

=============================================================

Union types

type Product {
 	id:Int
 	name:String
 	price:Float
}

type Tv {
	id:Int
 	name:String
 	price:Float
 	screenType:String
 }
 
 type Mobile {
	id:Int
 	name:String
 	price:Float
 	connectivity:String
 }
 
 union Products = Product | Tv | Mobile
 
 extend type Query {
 	 products: [Products]
 }
 
 
 
 	@Bean
	public SchemaParserDictionary getSchemaParserDictionary() {
		return new SchemaParserDictionary().add(Mobile.class).add(Tv.class).add(Product.class);
	}
	
	========

	query {
	products {
    __typename
    ... on Mobile {
      name
      price
      connectivity
    }
    ... on Tv {
      name
      screenType
    }
    
  }
}

Response:

{
  "data": {
    "products": [
      {
        "__typename": "Tv",
        "name": "Sony Bravia",
        "screenType": "LED"
      },
      {
        "__typename": "Mobile",
        "name": "MotoG",
        "price": 12999,
        "connectivity": "4G"
      },
      {
        "__typename": "Tv",
        "name": "Onida Thunder",
        "screenType": "CRT"
      },
      {
        "__typename": "Mobile",
        "name": "iPhone XR",
        "price": 99999,
        "connectivity": "4G"
      },
      {
        "__typename": "Mobile",
        "name": "Oppo",
        "price": 9999,
        "connectivity": "4G"
      }
    ]
  }
}

 
=============

type Query {}

type Mutation {}

type Subscription {}

=====================

Observer - Observable Design Pattern

Observable has a subject [ data can from different resources]
Observable is going to notify all observers ==> Observers can consume the data

Observable

	onNext(data) ==> emit the data

	onComplete() 

	onError()

------------------

StoreInSights
CustomerInSights

-----------------------

type Subscription {
 authors:Author!
}

type Author {
	id: Int,
	firstName: String,
	lastName: String,
	middleName :String
}


Subscriber gets notification whenever any mutation happens on Author data [update, create]



@Component
public class AuthorSubscription implements GraphQLSubscriptionResolver {
	
	@Autowired
	private AuthorPublisher publisher;
	
	public Publisher<Author> authors() {
		return publisher.getPublisher();
	}
}

---


@Component
public class AuthorPublisher {

    private final Flowable<Author> publisher;

    private ObservableEmitter<Author> emitter;
    
    public AuthorPublisher() {
        Observable<Author> authorObservable = Observable.create(emitter -> {
        	System.out.println("Emitter ==> " + emitter);
            this.emitter = emitter;
        });
        publisher = authorObservable.toFlowable(BackpressureStrategy.BUFFER);
    }

    public void publish(final Author author) {
        emitter.onNext(author);
    }


    public Flowable<Author> getPublisher() {
        return publisher;
    }
}



===

http://localhost:8080/graphiql
client:

subscription {
  authors {
    firstName
    lastName
  }
} 

===

Another client

mutation {
  createAuthor(author: {firstName :"Aaaa", lastName :"44"})
}

as soon as the mutation happens "1st" client gets notification

====================================================================

Secure GraphQL







