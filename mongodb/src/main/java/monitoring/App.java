package monitoring;

import com.mongodb.*;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import monitoring.config.MongoDbConfig;
import monitoring.data.CollectionCounters;
import monitoring.data.ServerCounters;
import monitoring.monitors.ServerMonitor;
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
    public static MongoDbConfig databaseConfig;

    public static void main( String[] args ) throws IOException {

        System.out.println( "Hello World!" );

        databaseConfig = new MongoDbConfig("localhost",27017,"test");

        DatabaseClient client = new DatabaseClient(databaseConfig);

        Thread serverMonitor = new Thread(new ServerMonitor(1,10));

        serverMonitor.start();



    }

}
