package com.mkyong.core;

import com.mongodb.*;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        MongoClient dbmongo = new MongoClient("localhost",27017);
        MongoDatabase database = dbmongo.getDatabase("test");
        MongoCollection<Document> restaurants = database.getCollection("restaurants");
        ListCollectionsIterable<Document> collections = database.listCollections();
        /*for (Document name: collections
             ) {
            System.out.println(name);
        }*/
        Document stats = database.runCommand(new Document("collStats","restaurants"));
        System.out.println(stats.toJson());
        System.out.println(stats.get("size"));

        Document serverStatus = database.runCommand(new Document("serverStatus","1"));
        System.out.println(serverStatus.toJson());

        Process mongoTop = Runtime.getRuntime().exec("C:\\Program Files\\MongoDB\\Server\\3.6\\bin\\mongotop.exe");
        BufferedReader input = new BufferedReader(new InputStreamReader(mongoTop.getInputStream()));
        String line;
        while((line=input.readLine())!=null){
            System.out.println(line);
        }
        input.close();

    }

}
