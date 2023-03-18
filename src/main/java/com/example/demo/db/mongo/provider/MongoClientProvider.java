package com.example.demo.db.mongo.provider;

import static com.mongodb.client.model.Filters.eq;

import java.util.Arrays;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

@Configuration
public class MongoClientProvider {
  Logger logger = LoggerFactory.getLogger(MongoClientProvider.class);

  public MongoClientProvider() {
    String uri = System.getenv("MONGODB_URI");
    MongoClient mongoClient = MongoClients.create(
        uri);

    MongoDatabase database = mongoClient.getDatabase("test");
    MongoCollection<Document> collection = database.getCollection("test");

    logger.info("Inserting doc...");
    try {
      InsertOneResult result = collection.insertOne(new Document()
          .append("_id", new ObjectId())
          .append("title", "Ski Bloopers")
          .append("genres", Arrays.asList("Documentary", "Comedy")));
      logger.info("Success! Inserted document id: " + result.getInsertedId());
    } catch (MongoException me) {
      logger.error("Unable to insert due to an error: " + me);
    }

    logger.info("Fetching doc...");
    Document doc = collection.find(eq("title", "Ski Bloopers")).first();
    if (doc == null) {
      logger.warn("No results found.");
    } else {
      logger.info(doc.toJson());
    }

    mongoClient.close();
    logger.info("mongoClient closed");
  }

}
