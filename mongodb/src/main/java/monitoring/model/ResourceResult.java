package monitoring.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ResourceResult<T>{
    private String key;
    private String databaseName;
    private T value;
    private RenderingType type;
    private Class<T> valueType;
    private List<DateValuePair<T>> historicalMonitoring;


    public ResourceResult(String key, T value, Class<T> valueType, List<DateValuePair<T>> historicalMonitoring) {
        this(key, key, value, RenderingType.DEFAULT, valueType, historicalMonitoring );
    }

    public ResourceResult(
            String key, String databaseName, T value, RenderingType renderingType, Class<T> valueType, List<DateValuePair<T>> historicalMonitoring
    ) {
        this.key = key;
        this.databaseName = databaseName;
        this.value = value;
        this.type = renderingType;
        this.valueType = valueType;
        this.historicalMonitoring = historicalMonitoring;
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

    public String getDatabaseName() {
        return databaseName;
    }

    public List<DateValuePair<T>> getHistoricalMonitoring() {
        return historicalMonitoring;
    }

    public void setHistoricalMonitoring(List<DateValuePair<T>> historicalMonitoring) {
        this.historicalMonitoring = historicalMonitoring;
    }
}
