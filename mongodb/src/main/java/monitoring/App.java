package monitoring;

import monitoring.config.MongoDbConfig;
import monitoring.data.ServerCounters;
import monitoring.monitors.CollectionMonitor;
import monitoring.monitors.ServerMonitor;
import monitoring.services.MongoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class App 
{
    public static MongoDbConfig databaseConfig = new MongoDbConfig("localhost",27017,"test");
    public static DatabaseClient client = new DatabaseClient(databaseConfig);

    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World from backed!" );

        Thread serverMonitor = new Thread(new ServerMonitor(30,10));
        serverMonitor.start();

        Thread restaurantsMonitor = new Thread(new CollectionMonitor(5,10,"restaurants"));
        restaurantsMonitor.start();
    }
}
