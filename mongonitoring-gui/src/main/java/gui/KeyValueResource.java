package gui;


import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

public class KeyValueResource {
    //todo: maybe in the future threshold
    private String key;
    private Component value;

    public KeyValueResource(String key, Component value) {
        this.key = key;
        this.value = value;
    }

    public Label getKey() {
        return new Label(key);
    }

    public Component getValue() {
        return value;
    }


}
