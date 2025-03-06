package com.sv.iamninja;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

	public static void main(String[] args) {
		// Replace with your MongoDB connection string
		String mongoUrl = "mongodb+srv://administrator:iYWjjpe38nGGRRXs@cluster0.vjcdm.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
		//MongoClient mongoClient = null;
		// Create a MongoDB client
		try ( MongoClient mongoClient = MongoClients.create(mongoUrl)) {
			// Access the database
			MongoDatabase database = mongoClient.getDatabase("pam_test");
		

	        // Create or access the collection (if it doesn't exist, it will be created)
	        MongoCollection<Document> collection = database.getCollection("users");

	        // Create a document to inse
	        Document document = new Document("name", "John Doe")
	                .append("email", "john@example.com")
	                .append("age", 33);

	        // Insert the document into th
	        collection.insertOne(document);

	        System.out.println("Document inserted successfully!");

	        // Close the MongoDB client
	      
			System.out.println("the name of the db is : " + database.getName());
			// Example operation: List collections
			// MongoDatabase database = mongoClient.getDatabase("pam_test");

		        // Access the collection
		        MongoCollection<Document> dbCollections = database.getCollection("users");
				System.out.println("Collection: " + dbCollections);
				MongoCursor<Document> cursor =dbCollections.find().iterator();

		        try {
		            // Iterate through the documents
		            while (cursor.hasNext()) {
		                Document doc = cursor.next();
		                System.out.println("Name: " + doc.getString("name"));
		                System.out.println("Email: " + doc.getString("email"));
		                System.out.println("Age: " + doc.getInteger("age"));
		                System.out.println("-------------------------------");
		            }
		        } finally {
		            // Close the cursor
		            cursor.close();
		        }
		
			mongoClient.close();
		} catch (Exception e) {
			System.err.println("An error occurred while connecting to MongoDB: " + e.getMessage());
		}
		finally
		{
			System.out.println("closing the mongo DB client...");
			  
		}
	}
}