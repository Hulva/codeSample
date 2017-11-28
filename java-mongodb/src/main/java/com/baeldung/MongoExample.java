package com.baeldung;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoExample {
	public static void main(String[] args) {

		try (MongoClient mongoClient = new MongoClient("192.168.1.248", 27017);) {

			MongoDatabase database = mongoClient.getDatabase("myMongoDb");

			// print existing databases
			MongoCursor<String> dbNames = mongoClient.listDatabaseNames().iterator();
			while (dbNames.hasNext()) {
				System.out.println(dbNames.next());
			}

//			database.createCollection("customers", null);

			// print all collections in customers database
			MongoCursor<String> collections = database.listCollectionNames().iterator();
			while (collections.hasNext()) {
				System.out.println(collections.next());
			}

			// create data
			MongoCollection<Document> collection = database.getCollection("customers");
			BasicDBObject document = new BasicDBObject();
			document.put("name", "Shubham");
			document.put("company", "Baeldung");
			collection.createIndex(document);

			// update data
			BasicDBObject query = new BasicDBObject();
			query.put("name", "Shubham");
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.put("name", "John");
			BasicDBObject updateObject = new BasicDBObject();
			updateObject.put("$set", newDocument);
			collection.updateOne(query, updateObject);

			// read data
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("name", "John");
			FindIterable<Document> documents = collection.find(searchQuery);
			MongoCursor<Document> cursor = documents.iterator();
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

			// delete data
			BasicDBObject deleteQuery = new BasicDBObject();
			deleteQuery.put("name", "John");
			collection.deleteOne(deleteQuery);
		}
	}
}
