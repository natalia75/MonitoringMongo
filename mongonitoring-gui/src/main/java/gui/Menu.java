package gui;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import static gui.Setup.setup;


public class Menu extends VerticalLayout {
    final private static float MENU_WIDTH_IN_PIXELS = 220.f;

    private Navigator navigator;
    private final VerticalLayout menuItemsContainer = setup(new VerticalLayout(), vl -> {
        vl.setWidth(100.0F, Unit.PERCENTAGE);
        vl.setHeightUndefined();
        vl.setMargin(false);
    });

    public Button addMenuBtn(String caption, String path){
        return setup(
            new Button(caption, v -> navigator.navigateTo(path)),
            btn -> {
                btn.setWidth(100.0F, Unit.PERCENTAGE);
                menuItemsContainer.addComponent(btn);
                //todo: add style
            }
        );
    }

    public Menu(Label mainLabel, Navigator navigator) {
        this.navigator = navigator;
        setMargin(false);
        mainLabel.setWidth(100, Unit.PERCENTAGE);
        addComponent(mainLabel);
        setHeight(100.0F, Unit.PERCENTAGE);
        setWidth(MENU_WIDTH_IN_PIXELS, Unit.PIXELS);
        addComponentsAndExpand(setup(new VerticalLayout(), vl -> {
            vl.setSizeFull();
            vl.setMargin(false);
            vl.addComponent(menuItemsContainer);
        }));
        addMenuBtn("MenuItem1", "test1");
        addMenuBtn("MenuItem2", "test2");
    }
}
