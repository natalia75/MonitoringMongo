package gui.monitoringViews;

import com.vaadin.ui.Label;
import gui.Guiable;
import monitoring.model.RenderingType;

import java.util.HashMap;
import java.util.Map;

public interface RenderMap{
     Map<RenderingType, Guiable> map = new HashMap<RenderingType, Guiable>() {
        {
            put(RenderingType.BasicKeyValue, resource ->
                    new Label(((resource.getValueType().cast(resource.getValue()))).toString())
            );
        }
    };

}
