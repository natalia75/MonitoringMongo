package monitoring.model;

import java.util.Optional;

public class ResourceResult<T>{
    private String key;
    private String databaseName;
    private T value;
    private RenderingType type;
    private Class<T> valueType;


    public ResourceResult(String key, T value, Class<T> valueType ) {
        this(key, key, value, RenderingType.DEFAULT, valueType);
    }

    public ResourceResult(String key, String databaseName, T value, RenderingType renderingType, Class<T> valueType) {
        this.key = key;
        this.databaseName = databaseName;
        this.value = value;
        this.type = renderingType;
        this.valueType = valueType;
    }

    public Class<T> getValueType() {
        return valueType;
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
