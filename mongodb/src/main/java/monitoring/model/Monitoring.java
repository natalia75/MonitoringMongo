package monitoring.model;

import java.util.List;
import java.util.stream.Collectors;

public class Monitoring {
    String monitoringName;
    List<Resource> resources;

    public Monitoring(String monitoringName, List<Resource> resources) {
        this.monitoringName = monitoringName;
        this.resources = resources;
    }

    public MonitoringResult toResult(){
        List<ResourceResult> results = resources.stream().map(Resource::result).collect(Collectors.toList());
        return new MonitoringResult(monitoringName, results);
    }
}
