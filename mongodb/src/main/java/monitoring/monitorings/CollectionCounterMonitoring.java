package monitoring.monitorings;

import monitoring.data.CollectionCounters;
import monitoring.model.Monitoring;
import monitoring.model.MonitoringResult;
import monitoring.model.Resource;
import org.bson.Document;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static monitoring.App.client;

public class CollectionCounterMonitoring extends CollectionCounters implements HasMonitoringResult{
    private final String MONITORING_NAME = "Collection counters";
    private Monitoring monitoring;

    public CollectionCounterMonitoring(String collectionName){
        this(collectionName, new Date());
    }

    public CollectionCounterMonitoring(String collectionName, Date timeStamp) {
        super(collectionName, timeStamp, client.getDatabase().runCommand(new Document("collStats", collectionName)));
        List<Resource> resources = new LinkedList<Resource>(){
            {
                push(new Resource<>("indexes", () -> getCollectionName(), String.class));
                push(new Resource<Date>("timeStamp", () -> getTimeStamp(), Date.class));
                push(new Resource<>("size", () -> getSize(), Integer.class ));
                push(new Resource<>("count", () -> getCount(), Integer.class));
                push(new Resource<>("avgObjSize", () -> getAvgObjSize(), Integer.class));
                push(new Resource<>("storageSize", () -> getStorageSize(), Integer.class));
                push(new Resource<>("nindexes", () -> getNindexes(), Integer.class));
                push(new Resource<>("indexes", () -> getIndexes(), Map.class));
                push(new Resource<>("totalIndexSize", () -> getTotalIndexSize(), Integer.class));
                push(new Resource<>("transactionUpdateConflict", () -> getTransactionUpdateConflict(), Integer.class));
            }
        };
        monitoring = new Monitoring(MONITORING_NAME, resources);
    }

    public MonitoringResult getResult(){
        return monitoring.toResult();
    }

}
