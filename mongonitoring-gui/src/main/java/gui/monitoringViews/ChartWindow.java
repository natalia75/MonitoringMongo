package gui.monitoringViews;

import com.vaadin.ui.Component;
import com.vaadin.ui.Window;
import monitoring.model.ResourceResult;

public class ChartWindow extends Window {
    public ChartWindow(String caption, ResourceResult<Double> rr) {
        super(caption, Chart.getLevelChart(rr, rr.getHistoricalMonitoring()));
        center();
    }
}
