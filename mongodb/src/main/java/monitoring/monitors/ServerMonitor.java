package monitoring.monitors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.deploy.util.SessionState;
import monitoring.DatabaseClient;
import monitoring.data.ServerCounters;
import org.bson.Document;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static monitoring.App.databaseConfig;

public class ServerMonitor implements Runnable  {
    private int period;
    private int historySize;
    private DatabaseClient client;
    List<ServerCounters> counters;
    private Date lastCountersReadtime;


    public ServerMonitor(int period, int historySize) {
        this.period = period;
        this.historySize = historySize;
        counters = new ArrayList<ServerCounters>();
        client = new DatabaseClient(databaseConfig);
        lastCountersReadtime = new Date();
    }

    private void getNewCounters(){
        Document serverStatus = client.getDatabase().runCommand(new Document("serverStatus","1"));
        ServerCounters sc = new ServerCounters(new Date(),serverStatus);
        if(counters.size()>historySize){
            counters.remove(0);
        }
        counters.add(sc);
        lastCountersReadtime = new Date();
    }

    public void run(){
        while(Thread.currentThread().isAlive()){
            Date currentDateTime = new Date();
            long div = (currentDateTime.getTime()-lastCountersReadtime.getTime())/1000;
            if(div>period) {
                getNewCounters();
                saveToFile();
            }else{
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveToFile(){
        try {
            Writer writer = new FileWriter("ServerCounters.JSON");
            Gson gson = new GsonBuilder().create();
            gson.toJson(counters,writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}