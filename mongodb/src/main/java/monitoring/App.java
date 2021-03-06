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
    public static ServerMonitor serverMonitor = new ServerMonitor(30,10);
    public static CollectionMonitor restaurantsMonitor =  new CollectionMonitor(5,10,"restaurants");

    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World from backed!" );

        Thread serverMonitorT = new Thread(serverMonitor);
        serverMonitorT.start();

        Thread restaurantsMonitorT = new Thread(restaurantsMonitor);
        restaurantsMonitorT.start();
    }
}
