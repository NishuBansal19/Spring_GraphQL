package com.adobe.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GraphQlExampleApplication.class)
public class TestApplication {

}
