package gui.monitoringViews;

import gui.KeyValueResource;
import monitoring.model.ResourceResult;
import monitoring.monitorings.CollectionCounterMonitoring;
import monitoring.monitorings.ServerCountersMonitoring;
import monitoring.services.MongoService;

public enum MonitoringViews {
    SERVER_COUNTERS(new MonitoringView(new ServerCountersMonitoring()), "server-counters"),
    COLLECTION_COUNTERS(new CollectionMonitoringView(
        str -> new CollectionCounterMonitoring(str).getResult(), new MongoService()
    ), "collections-counters");

    private MonitoringView monitoringView;
    private String path;

    MonitoringViews(MonitoringView monitoringView, String path) {
        monitoringView.init();
        this.path = path;
        this.monitoringView = monitoringView;
    }

    public MonitoringView getMonitoringView() {
        return monitoringView;
    }

    public String getPath() {
        return path;
    }

    public static KeyValueResource resourceToGui(ResourceResult r) {
        return new KeyValueResource(r.getKey(), RenderMap.map.get(r.getType()).toGui(r));
    }
}
