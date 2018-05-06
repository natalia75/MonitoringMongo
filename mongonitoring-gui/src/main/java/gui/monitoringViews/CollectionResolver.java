package gui.monitoringViews;

import monitoring.model.MonitoringResult;

public interface CollectionResolver {
    MonitoringResult getMonitoringResult(String monitoringName);
}
