package monitoring.model;

public class Resource<T> {
    private String guiName;
    private String dataBaseName;
    private ResourceGetter<T> resourceGetter;
    private RenderingType renderingType;

    public Resource(String guiName, String dataBaseName, ResourceGetter<T> resourceGetter, RenderingType renderingType) {
        this.guiName = guiName;
        this.dataBaseName = dataBaseName;
        this.resourceGetter = resourceGetter;
        this.renderingType = renderingType;
    }

    public Resource(String name, ResourceGetter<T> resourceGetter, RenderingType renderingType) {
        this(name, name, resourceGetter, renderingType);
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

    public ResourceResult<T> result(){
        return new ResourceResult<T>(guiName, dataBaseName, resourceGetter.get(), renderingType);
    }
}
