package gui;

import com.vaadin.server.VaadinService;

public interface GuiCommons {
    final static String BASEPATH = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
}
