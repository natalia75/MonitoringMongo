package monitoring.model;

import java.util.Optional;

public class ResourceResult<T>{
    private String key;
    private String databaseName;
    private T value;
    private RenderingType type;


    public ResourceResult(String key, T value) {
        this(key, key, value, RenderingType.DEFAULT);
    }

    public ResourceResult(String key, String databaseName, T value, RenderingType renderingType) {
        this.key = key;
        this.databaseName = databaseName;
        this.value = value;
        this.type = renderingType;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public RenderingType getType() {
        return type;
    }
}
