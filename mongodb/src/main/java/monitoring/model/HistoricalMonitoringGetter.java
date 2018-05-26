package monitoring.model;

import monitoring.monitors.AbstractMonitor;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class HistoricalMonitoringGetter {
    AbstractMonitor moniting;

    public HistoricalMonitoringGetter(AbstractMonitor moniting) {
        this.moniting = moniting;
    }

    <T> List<DateValuePair<T>> get(ResourceGetter<T> rg){
        return moniting.getHistoricalMoitoring(rg);
    }
}
