package gui;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.io.File;

import static gui.GuiCommons.BASEPATH;
import static gui.Setup.setup;

public class MenuManager extends VerticalLayout {
    final private static float MENU_WIDTH_IN_PIXELS = 240.f;
    final private static String FOOTER_CONTENT = "Created by Natalia Prokop & Bartosz Przybylski";


    public MenuManager(Navigator navigator, ComponentContainer cc) {
        final Panel changeableVl = setup(new Panel(cc), Panel::setSizeFull);
        final Menu menu = new Menu(new Label("<h2>Mongonitoring</h2>", ContentMode.HTML), navigator);

        final HorizontalLayout menuContainer = setup(new HorizontalLayout(), hl -> {
            hl.setSizeFull();
            hl.addComponent(menu);
            hl.addComponentsAndExpand(changeableVl);
        });

        final Component footer = new Panel(setup(new VerticalLayout(), vl -> {
            vl.setMargin(true);
            setup(new HorizontalLayout(), hl -> {
                hl.addComponent(setup(new VerticalLayout(), innerVl -> {
                    innerVl.setHeight(50.f, Unit.PIXELS);
                    innerVl.setPrimaryStyleName("agh-logo");
                    innerVl.addComponent(
                        new Image(null, new FileResource(new File(BASEPATH + "/resources/agh.jpg")))
                    );
                }));
                setup(new Label(FOOTER_CONTENT), label -> {
                    hl.addComponent(label);
                    hl.setComponentAlignment(label, Alignment.MIDDLE_RIGHT);
                });
                vl.addComponent(hl);
                vl.setComponentAlignment(hl, Alignment.MIDDLE_CENTER);
            });
        }));

        setSizeFull();
        addComponentsAndExpand(menuContainer);
        addComponent(footer);
    }


}
