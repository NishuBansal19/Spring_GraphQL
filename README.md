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

Resume @ 11:20







