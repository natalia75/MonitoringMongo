package monitoring;

import monitoring.config.MongoDbConfig;
import monitoring.data.ServerCounters;
import monitoring.monitors.ServerMonitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static MongoDbConfig databaseConfig;

    public static void main( String[] args ) throws IOException {

        System.out.println( "Hello World from backed!" );

        databaseConfig = new MongoDbConfig("localhost",27017,"test");

        DatabaseClient client = new DatabaseClient(databaseConfig);

        List<ServerCounters> serverCounters = new ArrayList<ServerCounters>();
        Thread serverMonitor = new Thread(new ServerMonitor(1,10,serverCounters));

        serverMonitor.start();

    }

}
