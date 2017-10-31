package com.jrz.microservices.books.configuration;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BooksConfiguration {
	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean() {
		return new DozerBeanMapper();
	}

	@Bean(name = "org.springframework.web.client.RestTemplate")
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
