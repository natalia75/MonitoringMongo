package gui;


import com.vaadin.ui.Label;
import monitoring.model.RenderingType;
import monitoring.model.ResourceResult;

import java.util.HashMap;
import java.util.Map;

public enum MonitoringViews {
    MONITORING_1(new TestMonitoring(), "test_resource");

    private MonitoringView monitoringView;
    private String path;
    MonitoringViews(MonitoringView monitoringView, String path){
        this.path = path;
        this.monitoringView = monitoringView;
    }
    private static Map<RenderingType, Guiable> map = new HashMap<RenderingType, Guiable>(){
        {
            put(RenderingType.BasicKeyValue, resource -> new Label(resource.toString()));
        }
    };

    public static KeyValueResource resourceToGui(ResourceResult r){
        return new KeyValueResource(r.getKey(), map.get(r.getType()).toGui(r));
    }
}
