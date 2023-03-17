package com.example.demo.db.mongo.provider;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoClientProvider {
  Logger logger = LoggerFactory.getLogger(MongoClientProvider.class);
  private MongoClient mongoClient;

  public MongoClientProvider() {
    init();
  }

  public MongoClient init() {
    String uri = System.getenv("MONGODB_URI");
    this.mongoClient = MongoClients.create(
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

    return this.mongoClient;

  }
}
