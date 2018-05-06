package monitoring;

import com.mongodb.ClientSessionOptions;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.session.ClientSession;
import monitoring.config.MongoDbConfig;

public class DatabaseClient {
    private MongoDbConfig configuration;
    private MongoClient client;
    private MongoDatabase database;
    private ClientSession session;

    public DatabaseClient(MongoDbConfig configuration) {
        this.configuration = configuration;
        client = new MongoClient(configuration.getHost(),configuration.getPort());
        database = client.getDatabase(configuration.getDatabaseName());
        session = client.startSession(ClientSessionOptions.builder().build());
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public ClientSession getSession() {
        return session;
    }
}
