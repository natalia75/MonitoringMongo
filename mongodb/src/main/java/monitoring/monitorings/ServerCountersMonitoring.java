package monitoring.monitorings;

import monitoring.data.ServerCounters;
import monitoring.model.Monitoring;
import monitoring.model.MonitoringResult;
import monitoring.model.Resource;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ServerCountersMonitoring extends ServerCounters implements HasMonitoringResult {

    private Monitoring monitoring;
    public ServerCountersMonitoring(){
        this(new Date());
    }
    public ServerCountersMonitoring(Date timeStamp){
        super(timeStamp, HasMonitoringResult.getDocument());
        List<Resource> resources = new LinkedList<Resource>(){
            {
                push(new Resource<>("timeStamp", () -> getTimeStamp(), Date.class));
                push(new Resource<>("host", () -> getHost(), String.class));
                push(new Resource<>("version", () -> getVersion(), String.class));
                push(new Resource<>("process", () -> getProcess(), String.class));
                push(new Resource<>("pid", () -> getPid(), Long.class));
                push(new Resource<>("uptime", () -> getUptime(), Double.class));
                push(new Resource<>("current connections", () -> getConnectionsCurrent(), Integer.class));
                push(new Resource<>("available connections",() -> getConnectionsAvailable(), Integer.class));
                push(new Resource<>("totalCreated",() -> getConnectionsTotalCreated(), Integer.class));
                push(new Resource<>("pageFaults",() -> getPageFaults(), Integer.class));
                push(new Resource<>("usagePageFileMB",() -> getUsagePageFileMB(), Integer.class));
                push(new Resource<>("totalPageFileMB", () -> getTotalPageFileMB(), Integer.class));
                push(new Resource<>("availablePageFileMB", () -> getAvailablePageFileMB(), Integer.class));
                push(new Resource<>("ramMB", () -> getRamMB(), Integer.class));
                push(new Resource<>("currentQueueTotal", () -> getCurrentQueueTotal(), Integer.class));
                push(new Resource<>("currentQueueReaders", () -> getCurrentQueueReaders(), Integer.class));
                push(new Resource<>("currentQueueWriters", () -> getCurrentQueueWriters(), Integer.class));
                push(new Resource<>("activeClientsTotal", () -> getActiveClientsTotal(), Integer.class));
                push(new Resource<>("activeClientsReaders", () -> getActiveClientsReaders(), Integer.class));
                push(new Resource<>("activeClientsWriters", () -> getActiveClientsWriters(), Integer.class));
                push(new Resource<>("numRequests", () -> getNumRequests(), Long.class));
                push(new Resource<>("readersLatency", () -> getReadersLatency(), Long.class));
                push(new Resource<>("writersLatency", () -> getWritersLatency(), Long.class));
                push(new Resource<>("commandsLatency", () -> getCommandsLatency(), Long.class));
                push(new Resource<>("opCountersInsert", () -> getOpCountersInsert(), Integer.class));
                push(new Resource<>("opCountersQuery", () -> getOpCountersQuery(), Integer.class));
                push(new Resource<>("opCountersUpdate", () -> getOpCountersUpdate(), Integer.class));
                push(new Resource<>("opCountersDelete", () -> getOpCountersDelete(), Integer.class));
                push(new Resource<>("memBits", () -> getMemBits(), Integer.class));
                push(new Resource<>("memResident", () -> getMemResident(), Integer.class));
                push(new Resource<>("memVirtual", () -> getMemVirtual(), Integer.class));
            }
        };
        monitoring = new Monitoring("Server counters", resources);
    }

    @Override
    public MonitoringResult getResult() {
        return monitoring.toResult();
    }
}
