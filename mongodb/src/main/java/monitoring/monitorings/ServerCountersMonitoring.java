package monitoring.monitorings;

import monitoring.App;
import monitoring.data.ServerCounters;
import monitoring.model.HistoricalMonitoringGetter;
import monitoring.model.Monitoring;
import monitoring.model.MonitoringResult;
import monitoring.model.Resource;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ServerCountersMonitoring extends ServerCounters implements HasMonitoringResult {

    private Monitoring monitoring;
    private HistoricalMonitoringGetter monitor = new HistoricalMonitoringGetter(App.serverMonitor);
    public ServerCountersMonitoring(){
        this(new Date());
    }
    public ServerCountersMonitoring(Date timeStamp){
        super(timeStamp, HasMonitoringResult.getDocument());
        List<Resource> resources = new LinkedList<Resource>(){
            {
                push(new Resource<>("timeStamp", () -> getTimeStamp(), Date.class, monitor));
                push(new Resource<>("host", () -> getHost(), String.class, monitor));
                push(new Resource<>("version", () -> getVersion(), String.class, monitor));
                push(new Resource<>("process", () -> getProcess(), String.class, monitor));
                push(new Resource<>("pid", () -> getPid(), Long.class, monitor));
                push(new Resource<>("uptime", () -> getUptime(), Double.class, monitor));
                push(new Resource<>("current connections", () -> getConnectionsCurrent(), Integer.class, monitor));
                push(new Resource<>("available connections",() -> getConnectionsAvailable(), Integer.class, monitor));
                push(new Resource<>("totalCreated",() -> getConnectionsTotalCreated(), Integer.class, monitor));
                push(new Resource<>("pageFaults",() -> getPageFaults(), Integer.class, monitor));
                push(new Resource<>("usagePageFileMB",() -> getUsagePageFileMB(), Integer.class, monitor));
                push(new Resource<>("totalPageFileMB", () -> getTotalPageFileMB(), Integer.class, monitor));
                push(new Resource<>("availablePageFileMB", () -> getAvailablePageFileMB(), Integer.class, monitor));
                push(new Resource<>("ramMB", () -> getRamMB(), Integer.class, monitor));
                push(new Resource<>("currentQueueTotal", () -> getCurrentQueueTotal(), Integer.class, monitor));
                push(new Resource<>("currentQueueReaders", () -> getCurrentQueueReaders(), Integer.class, monitor));
                push(new Resource<>("currentQueueWriters", () -> getCurrentQueueWriters(), Integer.class, monitor));
                push(new Resource<>("activeClientsTotal", () -> getActiveClientsTotal(), Integer.class, monitor));
                push(new Resource<>("activeClientsReaders", () -> getActiveClientsReaders(), Integer.class, monitor));
                push(new Resource<>("activeClientsWriters", () -> getActiveClientsWriters(), Integer.class, monitor));
                push(new Resource<>("numRequests", () -> getNumRequests(), Long.class, monitor));
                push(new Resource<>("readersLatency", () -> getReadersLatency(), Long.class, monitor));
                push(new Resource<>("writersLatency", () -> getWritersLatency(), Long.class, monitor));
                push(new Resource<>("commandsLatency", () -> getCommandsLatency(), Long.class, monitor));
                push(new Resource<>("opCountersInsert", () -> getOpCountersInsert(), Integer.class, monitor));
                push(new Resource<>("opCountersQuery", () -> getOpCountersQuery(), Integer.class, monitor));
                push(new Resource<>("opCountersUpdate", () -> getOpCountersUpdate(), Integer.class, monitor));
                push(new Resource<>("opCountersDelete", () -> getOpCountersDelete(), Integer.class, monitor));
                push(new Resource<>("memBits", () -> getMemBits(), Integer.class, monitor));
                push(new Resource<>("memResident", () -> getMemResident(), Integer.class, monitor));
                push(new Resource<>("memVirtual", () -> getMemVirtual(), Integer.class, monitor));
            }
        };
        monitoring = new Monitoring("Server counters", resources);
    }

    @Override
    public MonitoringResult getResult() {
        return monitoring.toResult();
    }
}
