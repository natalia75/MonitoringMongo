package gui;

import com.vaadin.navigator.View;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import monitoring.model.MonitoringResult;
import monitoring.model.ResourceResult;

abstract class MonitoringView extends VerticalLayout implements View {
    private MonitoringResult monitoringResult;
    private GridLayout gridLayout;

    public MonitoringView(){
        setSizeFull();
        addComponent(new Label(monitoringResult.getName()));
        final int rows = monitoringResult.getResourceResults().size();
        gridLayout = new GridLayout(3, rows);
        VerticalLayout separator = new VerticalLayout();
        separator.setWidth(40, Unit.PIXELS);
        gridLayout.addComponent(separator, 1, 0, 1, rows - 1);
        int i = 0;
        for(ResourceResult resourceResult : monitoringResult.getResourceResults()){
            KeyValueResource kvr = MonitoringViews.resourceToGui(resourceResult);
            i++;
            gridLayout.addComponent(kvr.getKey(), 0, i);
            gridLayout.addComponent(kvr.getValue(), 2, i);
        }
        addComponent(gridLayout);
    }

}
