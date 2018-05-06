package monitoring.services;

import com.mongodb.client.MongoIterable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static monitoring.App.client;

public class MongoService {
    public List<String> getAllMongoColections() {
        return toList(client.getDatabase().listCollectionNames());
    }

    public static <T> List<T> toList(MongoIterable<T> mongoIterable) {
        List<T> target = new ArrayList<>();
        mongoIterable.forEach((Consumer<T>) target::add);
        return target;
    }

}
