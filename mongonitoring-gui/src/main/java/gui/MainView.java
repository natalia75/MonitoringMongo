package gui;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class MainView extends VerticalLayout implements View {
    public MainView(){
        addComponent(new Label("glowny widok"));
    }
}
