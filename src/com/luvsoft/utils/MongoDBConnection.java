package com.luvsoft.utils;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDBConnection {
    public static MongoClient mongoClient;
    public static DB database;

    /*
     * @name connectMongoDB function
     */
    public void connectMongoDB() {
        try {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb://test:test@ds041992.mongolab.com:41992/test_database");
            mongoClient = new MongoClient(uri);
            database = mongoClient.getDB(uri.getDatabase());
            if (database != null) {
                System.out.println("Connected to MongoDB successfully!....");
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
