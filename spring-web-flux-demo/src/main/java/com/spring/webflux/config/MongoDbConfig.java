package com.spring.webflux.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;


public class MongoDbConfig extends AbstractReactiveMongoConfiguration {
	
	@Value("${port}")
	private String port;
	
	@Value("${dbname}")
	private String dbname;
	
	@Override
	public MongoClient reactiveMongoClient() {
		return MongoClients.create();
	}
	
	@Override
	protected String getDatabaseName() {
        return dbname;
    } 
	
	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() {
		return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
	}
}
