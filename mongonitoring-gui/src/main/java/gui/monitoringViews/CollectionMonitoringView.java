package gui.monitoringViews;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import monitoring.services.MongoService;

import java.util.List;

import static gui.Setup.setup;

public class CollectionMonitoringView extends MonitoringView {
    private ComboBox<String> combo;
    private VerticalLayout changebleVl;
    private CollectionResolver resolver;
    private List<String> collectionsList;

    private ComboBox<String> initializeCombo() {
        combo = new ComboBox<>();
        combo.setValue(collectionsList.get(0));
        combo.setEmptySelectionAllowed(false);
        combo.addValueChangeListener(event -> replaceCollectionGrid(combo.getValue()));
        combo.setItems(collectionsList);
        return combo;
    }

    @Override
    protected Layout wrapWithHeader(GridLayout gridLayout) {
        return setup(new VerticalLayout(), vl -> {
            vl.setMargin(false);
            vl.setSizeFull();
            vl.addComponent(
                setup(new HorizontalLayout(), hl -> {
                    hl.setWidth(100.f, Unit.PERCENTAGE);
                    setup(
                        new Label("<h3>" + guiName + "</h3>", ContentMode.HTML),
                        label -> {
                            label.setWidth(100.f, Unit.PERCENTAGE);
                            hl.addComponent(label);
                            hl.setExpandRatio(label, 1.0f);
                        }
                    );
                    setup(
                        new Label("Collection: "),
                        label -> {
                            hl.addComponent(label);
                            hl.setComponentAlignment(label, Alignment.MIDDLE_RIGHT);
                        }
                    );
                    hl.addComponent(initializeCombo());
                    hl.setComponentAlignment(combo, Alignment.MIDDLE_RIGHT);
                })
            );

            setup(
                new Panel(setup(new VerticalLayout(), innerVl -> {
                    innerVl.setMargin(true);
                    innerVl.addComponent(gridLayout);
                    changebleVl = innerVl;
                })),
                panel -> {
                    panel.setSizeFull();
                    vl.addComponent(panel);
                    vl.setExpandRatio(panel, 1.0f);
                }
            );
            addComponent(vl);
        });
    }

    CollectionMonitoringView(CollectionResolver resolver, MongoService mongoService) {
        //TODO: null control use: Optional
        super(() -> resolver.getMonitoringResult(mongoService.getAllMongoColections().get(0)));
        this.collectionsList = mongoService.getAllMongoColections();
        this.resolver = resolver;
    }

    private void replaceCollectionGrid(String newCollectionName) {
        setMonitoringResult(resolver.getMonitoringResult(newCollectionName));
        changebleVl.removeAllComponents();
        changebleVl.addComponent(buildGrid());
    }

}
