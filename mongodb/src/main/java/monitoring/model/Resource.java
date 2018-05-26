package monitoring.model;

import monitoring.monitors.AbstractMonitor;

public class Resource<T> {
    private String guiName;
    private String dataBaseName;
    private ResourceGetter<T> resourceGetter;
    private HistoricalMonitoringGetter historicalMonitoringGetter;
    private RenderingType renderingType;
    private final Class<T> type;

    public Resource(
            String guiName, String dataBaseName, ResourceGetter<T> resourceGetter, RenderingType renderingType, Class<T> tClass, HistoricalMonitoringGetter historicalMonitoringGetter
    ) {
        this.guiName = guiName;
        this.dataBaseName = dataBaseName;
        this.resourceGetter = resourceGetter;
        this.renderingType = renderingType;
        this.type = tClass;
        this.historicalMonitoringGetter = historicalMonitoringGetter;
    }

    public Resource(String name, ResourceGetter<T> resourceGetter, RenderingType renderingType, Class<T> tClass, HistoricalMonitoringGetter historicalMonitoringGetter) {
        this(name, name, resourceGetter, renderingType, tClass, historicalMonitoringGetter);
    }
    public Resource(String name, ResourceGetter<T> resourceGetter, Class<T> tClass, HistoricalMonitoringGetter historicalMonitoringGetter) {
        this(name, name, resourceGetter, RenderingType.DEFAULT, tClass, historicalMonitoringGetter);
    }


    public String getGuiName() {
        return guiName;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public ResourceGetter<T> getResourceGetter() {
        return resourceGetter;
    }

    public RenderingType getRenderingType() {
        return renderingType;
    }

    public Class<T> getType() {
        return type;
    }

    public ResourceResult<T> result(){
        return new ResourceResult<T>(guiName, dataBaseName, resourceGetter.get(), renderingType, type, historicalMonitoringGetter.get(resourceGetter));
    }
}
