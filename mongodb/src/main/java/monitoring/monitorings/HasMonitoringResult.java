package monitoring.monitorings;

import monitoring.App;
import monitoring.model.MonitoringResult;
import org.bson.Document;

public interface HasMonitoringResult {
    MonitoringResult getResult();
    static Document getDocument() {
        return App.client.getDatabase().runCommand(new Document("serverStatus","1"));
    }
}
