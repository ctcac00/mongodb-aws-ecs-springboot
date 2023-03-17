package com.example.demo;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@SpringBootApplication
public class DemoApplication {

	Logger logger = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			String uri = System.getenv("MONGODB_URI");
			MongoClient mongoClient = MongoClients.create(
					uri);

			MongoDatabase database = mongoClient.getDatabase("sample_mflix");
			MongoCollection<Document> collection = database.getCollection("movies");
			logger.info("Fetching doc...");
			Document doc = collection.find().first();
			if (doc == null) {
				logger.warn("No results found.");
			} else {
				logger.info(doc.toJson());
			}

			mongoClient.close();
			logger.info("mongoClient closed");

		};
	}

}
