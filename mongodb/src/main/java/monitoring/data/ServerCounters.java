package monitoring.data;


import org.bson.Document;

import javax.print.Doc;
import java.util.Date;
import java.util.Map;

public class ServerCounters {
    private Date timeStamp;
    private String host;
    private String version;
    private String process;
    private long pid;
    private double uptime;
    private int connectionsCurrent;
    private int connectionsAvailable;
    private int connectionsTotalCreated;
    private int pageFaults;
    private int usagePageFileMB;
    private int totalPageFileMB;
    private int availablePageFileMB;
    private int ramMB;
    private int currentQueueTotal;
    private int currentQueueReaders;
    private int currentQueueWriters;
    private int activeClientsTotal;
    private int activeClientsReaders;
    private int activeClientsWriters;
    private long numRequests;
    private long readersLatency;
    private long writersLatency;
    private long commandsLatency;
    private int opCountersInsert;
    private int opCountersQuery;
    private int opCountersUpdate;
    private int opCountersDelete;
    private int memBits;
    private int memResident;
    private int memVirtual;

    @Override
    public String toString() {
        return "ServerCounters{" +
                "host='" + host + '\'' +
                ", version='" + version + '\'' +
                ", process='" + process + '\'' +
                ", pid=" + pid +
                ", uptime=" + uptime +
                ", connections= {current:" + connectionsCurrent +
                "; available: " + connectionsAvailable +
                "; totalCreated: " + connectionsTotalCreated +
                "} " +
                ", pageFaults=" + pageFaults +
                ", usagePageFileMB=" + usagePageFileMB +
                ", totalPageFileMB=" + totalPageFileMB +
                ", availablePageFileMB=" + availablePageFileMB +
                ", ramMB=" + ramMB +
                ", currentQueueTotal=" + currentQueueTotal +
                ", currentQueueReaders=" + currentQueueReaders +
                ", currentQueueWriters=" + currentQueueWriters +
                ", activeClientsTotal=" + activeClientsTotal +
                ", activeClientsReaders=" + activeClientsReaders +
                ", activeClientsWriters=" + activeClientsWriters +
                ", numRequests=" + numRequests +
                ", readersLatency=" + readersLatency +
                ", writersLatency=" + writersLatency +
                ", commandsLatency=" + commandsLatency +
                ", opCountersInsert=" + opCountersInsert +
                ", opCountersQuery=" + opCountersQuery +
                ", opCountersUpdate=" + opCountersUpdate +
                ", opCountersDelete=" + opCountersDelete +
                ", memBits=" + memBits +
                ", memResident=" + memResident +
                ", memVirtual=" + memVirtual +
                '}';
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public double getUptime() {
        return uptime;
    }

    public void setUptime(double uptime) {
        this.uptime = uptime;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getConnectionsCurrent() {
        return connectionsCurrent;
    }

    public void setConnectionsCurrent(int connectionsCurrent) {
        this.connectionsCurrent = connectionsCurrent;
    }

    public int getConnectionsAvailable() {
        return connectionsAvailable;
    }

    public void setConnectionsAvailable(int connectionsAvailable) {
        this.connectionsAvailable = connectionsAvailable;
    }

    public int getConnectionsTotalCreated() {
        return connectionsTotalCreated;
    }

    public void setConnectionsTotalCreated(int connectionsTotalCreated) {
        this.connectionsTotalCreated = connectionsTotalCreated;
    }

    public int getPageFaults() {
        return pageFaults;
    }

    public void setPageFaults(int pageFaults) {
        this.pageFaults = pageFaults;
    }

    public int getUsagePageFileMB() {
        return usagePageFileMB;
    }

    public void setUsagePageFileMB(int usagePageFileMB) {
        this.usagePageFileMB = usagePageFileMB;
    }

    public int getTotalPageFileMB() {
        return totalPageFileMB;
    }

    public void setTotalPageFileMB(int totalPageFileMB) {
        this.totalPageFileMB = totalPageFileMB;
    }

    public int getAvailablePageFileMB() {
        return availablePageFileMB;
    }

    public void setAvailablePageFileMB(int availablePageFileMB) {
        this.availablePageFileMB = availablePageFileMB;
    }

    public int getRamMB() {
        return ramMB;
    }

    public void setRamMB(int ramMB) {
        this.ramMB = ramMB;
    }

    public int getCurrentQueueTotal() {
        return currentQueueTotal;
    }

    public void setCurrentQueueTotal(int currentQueueTotal) {
        this.currentQueueTotal = currentQueueTotal;
    }

    public int getCurrentQueueReaders() {
        return currentQueueReaders;
    }

    public void setCurrentQueueReaders(int currentQueueReaders) {
        this.currentQueueReaders = currentQueueReaders;
    }

    public int getCurrentQueueWriters() {
        return currentQueueWriters;
    }

    public void setCurrentQueueWriters(int currentQueueWriters) {
        this.currentQueueWriters = currentQueueWriters;
    }

    public int getActiveClientsTotal() {
        return activeClientsTotal;
    }

    public void setActiveClientsTotal(int activeClientsTotal) {
        this.activeClientsTotal = activeClientsTotal;
    }

    public int getActiveClientsReaders() {
        return activeClientsReaders;
    }

    public void setActiveClientsReaders(int activeClientsReaders) {
        this.activeClientsReaders = activeClientsReaders;
    }

    public int getActiveClientsWriters() {
        return activeClientsWriters;
    }

    public void setActiveClientsWriters(int activeClientsWriters) {
        this.activeClientsWriters = activeClientsWriters;
    }

    public long getNumRequests() {
        return numRequests;
    }

    public void setNumRequests(long numRequests) {
        this.numRequests = numRequests;
    }

    public long getReadersLatency() {
        return readersLatency;
    }

    public void setReadersLatency(long readersLatency) {
        this.readersLatency = readersLatency;
    }

    public long getWritersLatency() {
        return writersLatency;
    }

    public void setWritersLatency(long writersLatency) {
        this.writersLatency = writersLatency;
    }

    public long getCommandsLatency() {
        return commandsLatency;
    }

    public void setCommandsLatency(long commandsLatency) {
        this.commandsLatency = commandsLatency;
    }

    public int getOpCountersInsert() {
        return opCountersInsert;
    }

    public void setOpCountersInsert(int opCountersInsert) {
        this.opCountersInsert = opCountersInsert;
    }

    public int getOpCountersQuery() {
        return opCountersQuery;
    }

    public void setOpCountersQuery(int opCountersQuery) {
        this.opCountersQuery = opCountersQuery;
    }

    public int getOpCountersUpdate() {
        return opCountersUpdate;
    }

    public void setOpCountersUpdate(int opCountersUpdate) {
        this.opCountersUpdate = opCountersUpdate;
    }

    public int getOpCountersDelete() {
        return opCountersDelete;
    }

    public void setOpCountersDelete(int opCountersDelete) {
        this.opCountersDelete = opCountersDelete;
    }

    public int getMemBits() {
        return memBits;
    }

    public void setMemBits(int memBits) {
        this.memBits = memBits;
    }

    public int getMemResident() {
        return memResident;
    }

    public void setMemResident(int memResident) {
        this.memResident = memResident;
    }

    public int getMemVirtual() {
        return memVirtual;
    }

    public void setMemVirtual(int memVirtual) {
        this.memVirtual = memVirtual;
    }


    public ServerCounters(Date timeStamp,Document document) {
        this.timeStamp = timeStamp;
        this.host = document.getString("host");
        this.version = document.getString("version");
        this.process = document.getString("process");
        this.pid = document.getLong("pid");
        this.uptime = document.getDouble("uptime");
        this.connectionsCurrent = ((Document)document.get("connections")).getInteger("current");
        this.connectionsAvailable = ((Document)document.get("connections")).getInteger("available");
        this.connectionsTotalCreated = ((Document)document.get("connections")).getInteger("totalCreated");
        this.pageFaults = ((Document)document.get("extra_info")).getInteger("page_faults");
        this.usagePageFileMB = ((Document)document.get("extra_info")).getInteger("usagePageFileMB");
        this.totalPageFileMB = ((Document)document.get("extra_info")).getInteger("totalPageFileMB");
        this.availablePageFileMB = ((Document)document.get("extra_info")).getInteger("availPageFileMB");
        this.ramMB = ((Document)document.get("extra_info")).getInteger("ramMB");
        Document globalLock = (Document)document.get("globalLock");
        this.currentQueueTotal = ((Document)globalLock.get("currentQueue")).getInteger("total");
        this.currentQueueReaders = ((Document)globalLock.get("currentQueue")).getInteger("readers");
        this.currentQueueWriters = ((Document)globalLock.get("currentQueue")).getInteger("writers");
        this.activeClientsTotal = ((Document)globalLock.get("activeClients")).getInteger("total");
        this.activeClientsReaders = ((Document)globalLock.get("activeClients")).getInteger("readers");
        this.activeClientsWriters = ((Document)globalLock.get("activeClients")).getInteger("writers");
        Document network = (Document)document.get("network");
        this.numRequests = network.getLong("numRequests");
        Document opLatencies = (Document)document.get("opLatencies");
        this.readersLatency = ((Document)opLatencies.get("reads")).getLong("latency");
        this.writersLatency = ((Document)opLatencies.get("writes")).getLong("latency");
        this.commandsLatency = ((Document)opLatencies.get("commands")).getLong("latency");
        this.opCountersInsert = ((Document)document.get("opcounters")).getInteger("insert");
        this.opCountersQuery = ((Document)document.get("opcounters")).getInteger("query");
        this.opCountersUpdate = ((Document)document.get("opcounters")).getInteger("update");
        this.opCountersDelete = ((Document)document.get("opcounters")).getInteger("delete");
        this.memBits =((Document)document.get("mem")).getInteger("bits");
        this.memResident =((Document)document.get("mem")).getInteger("resident");
        this.memVirtual =((Document)document.get("mem")).getInteger("virtual");
    }
}
