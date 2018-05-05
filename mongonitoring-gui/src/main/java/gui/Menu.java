package gui;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import static gui.Setup.setup;


public class Menu extends VerticalLayout {
    public Menu(Label mainLabel, Navigator navigator) {
        setMargin(false);
        addComponent(mainLabel);
        setHeight(100.0F, Unit.PERCENTAGE);
        setWidth(200.0F, Unit.PIXELS);
        addComponentsAndExpand(setup(new VerticalLayout(), vl -> {
            vl.addComponent(new Button("MenuItem1", v -> navigator.navigateTo("test1")));
            vl.addComponent(new Button("MenuItem2", v -> navigator.navigateTo("test2")));
        }));
    }
}
