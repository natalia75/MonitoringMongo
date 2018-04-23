package monitoring;

import com.mongodb.*;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import monitoring.config.MongoDbConfig;
import monitoring.data.CollectionCounters;
import monitoring.data.ServerCounters;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        DatabaseClient client = new DatabaseClient(new MongoDbConfig("localhost",27017,"test"));


//        MongoClient dbmongo = new MongoClient("localhost",27017);
//        MongoDatabase database = dbmongo.getDatabase("test");
//        MongoCollection<Document> restaurants = database.getCollection("restaurants");
//        ListCollectionsIterable<Document> collections = database.listCollections();listCollections

        Document stats = client.getDatabase().runCommand(new Document("collStats","restaurants"));
        CollectionCounters firstCounter = new CollectionCounters("restaurants", new Date(),stats);
        System.out.println("firstCounter: " + firstCounter.toString());
        System.out.println(stats.toJson());
        System.out.println(stats.get("size"));

        Document serverStatus = client.getDatabase().runCommand(new Document("serverStatus","1"));
        ServerCounters sc = new ServerCounters(new Date(),serverStatus);
        System.out.println(serverStatus.toJson());
        System.out.println(sc.toString());

        Process mongoTop = Runtime.getRuntime().exec("C:\\Program Files\\MongoDB\\Server\\3.6\\bin\\mongotop.exe");
        BufferedReader input = new BufferedReader(new InputStreamReader(mongoTop.getInputStream()));
        String line;
        while((line=input.readLine())!=null){
            System.out.println(line);
        }
        input.close();

    }

}
