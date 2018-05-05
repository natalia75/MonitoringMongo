package monitoring.model;

import java.util.List;

public class MonitoringResult {
    private String name;
    private List<ResourceResult> resourceResults;

    public MonitoringResult(String name, List<ResourceResult> resourceResults) {
        this.name = name;
        this.resourceResults = resourceResults;
    }
    //todo: saving to database by name, with resuorceDbNames


    public String getName() {
        return name;
    }

    public List<ResourceResult> getResourceResults() {
        return resourceResults;
    }
}
