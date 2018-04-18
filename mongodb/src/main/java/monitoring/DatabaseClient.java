package monitoring;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import monitoring.config.MongoDbConfig;

public class DatabaseClient {
    private MongoDbConfig configuration;
    private MongoClient client;
    private MongoDatabase database;


    public DatabaseClient(MongoDbConfig configuration) {
        this.configuration = configuration;
        client = new MongoClient(configuration.getHost(),configuration.getPort());
        database = client.getDatabase(configuration.getDatabaseName());
    }

    public MongoDatabase getDatabase() {
        return database;
    }


}
