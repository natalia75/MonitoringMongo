package gui.monitoringViews;

import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import gui.KeyValueResource;
import monitoring.model.MonitoringResult;
import monitoring.model.ResourceResult;
import monitoring.monitorings.HasMonitoringResult;

import static gui.Setup.setup;

public class MonitoringView extends VerticalLayout implements View {
    private MonitoringResult monitoringResult;
    protected String guiName;

    protected Layout wrapWithHeader(GridLayout gridLayout){
        return setup(new VerticalLayout(), vl -> {
            vl.setMargin(false);
            vl.setSizeFull();
            vl.addComponent(setup(
                new Label("<h3>" + guiName + "</h3>", ContentMode.HTML),
                label -> label.setWidth(100.f, Unit.PERCENTAGE)
            ));
            setup(
                new Panel(setup(new VerticalLayout(), innerVl -> {
                    innerVl.setMargin(true);
                    innerVl.addComponent(gridLayout);
                })),
                panel -> {
                    panel.setSizeFull();
                    vl.addComponent(panel);
                    vl.setExpandRatio(panel, 1.0f);
                }
            );
            addComponent(vl);
        });
    };

    protected GridLayout buildGrid(){
        final int rows = monitoringResult.getResourceResults().size();
        return setup(new GridLayout(5, rows), gridLayout -> {
            gridLayout.setMargin(false);
            gridLayout.setSizeFull();
            VerticalLayout separator = new VerticalLayout();
            separator.setWidth(20, Unit.PIXELS);
            VerticalLayout separator2 = new VerticalLayout();
            separator2.setWidth(20, Unit.PIXELS);
            gridLayout.addComponent(separator, 1, 0, 1, rows - 1);
            gridLayout.addComponent(separator2, 3, 0, 3, rows - 1);
            int i = 0;
            for (ResourceResult resourceResult : monitoringResult.getResourceResults()) {
                KeyValueResource kvr = MonitoringViews.resourceToGui(resourceResult);
                gridLayout.addComponent(kvr.getKey(), 0, i);
                gridLayout.addComponent(kvr.getValue(), 2, i);
                final int index = i;
                if(resourceResult.getValueType() == Double.class){
                    Button open = new Button("Open historical monitoring");
                    open.addClickListener(event -> {
                        ChartWindow sub = new ChartWindow(resourceResult.getKey(), resourceResult);
                        // Add it to the root component
                        UI.getCurrent().addWindow(sub);
                    });
                    gridLayout.addComponent(open, 4, index);
                }
                if(resourceResult.getValueType() == Integer.class){
                    Button open = new Button("Open historical monitoring");
                    open.addClickListener(event -> {
                        ChartWindow sub = new ChartWindow(resourceResult.getKey(), resourceResult);
                        // Add it to the root component
                        UI.getCurrent().addWindow(sub);
                    });
                    gridLayout.addComponent(open, 4, index);
                }

                i++;
            }
        });
    }

    MonitoringView(HasMonitoringResult hasMonitoringResult){
        monitoringResult = hasMonitoringResult.getResult();
        guiName = monitoringResult.getName();
        setMargin(false);
        setSizeFull();
    }

    public void init(){
        wrapWithHeader(buildGrid());
    }

    public String getGuiName() {
        return guiName;
    }

    public MonitoringResult setMonitoringResult(MonitoringResult monitoringResult) {
        this.monitoringResult = monitoringResult;
        return this.monitoringResult;
    }
}
