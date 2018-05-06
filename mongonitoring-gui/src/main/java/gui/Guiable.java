package gui;

import com.vaadin.ui.Component;
import monitoring.model.ResourceResult;

public interface Guiable {
    public Component toGui(ResourceResult resource);
}
