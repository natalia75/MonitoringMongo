package gui;

import com.vaadin.ui.Component;

public interface Guiable {
    public Component toGui(Object resource);
}
