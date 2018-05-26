package monitoring.monitors;

import monitoring.model.DateValuePair;
import monitoring.model.HistoricalMonitoringGetter;
import monitoring.model.Resource;
import monitoring.model.ResourceGetter;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AbstractMonitor {
    public <T> List<DateValuePair<T>> getHistoricalMoitoring(ResourceGetter<T> resource);
}
