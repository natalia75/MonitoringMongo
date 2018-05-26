package monitoring.monitors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import monitoring.DatabaseClient;
import monitoring.data.ServerCounters;
import monitoring.model.DateValuePair;
import monitoring.model.Resource;
import monitoring.model.ResourceGetter;
import org.bson.Document;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static monitoring.App.databaseConfig;

public class ServerMonitor implements AbstractMonitor, Runnable  {
    private int period;
    private int historySize;
    private DatabaseClient client;
    List<ServerCounters> counters;
    private Date lastCountersReadtime;

    public List<ServerCounters> getCounters() {
        return counters;
    }

    public ServerMonitor(int period, int historySize) {
        this.period = period;
        this.historySize = historySize;
        this.counters = new ArrayList<>();
        client = new DatabaseClient(databaseConfig);
        lastCountersReadtime = new Date();
        loadHistoryFromFile();
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


    public <T> List<DateValuePair<T>> getHistoricalMoitoring(ResourceGetter<T> resource){
        loadHistoryFromFile();
        return counters.stream().map(
                sc -> new DateValuePair<>(sc.getTimeStamp(), resource.get())
        ).collect(Collectors.toList());
    }

    private static String filename = "ServerCounters.JSON";

    private void loadHistoryFromFile(){
        String historyString = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            try {
                StringBuilder builder = new StringBuilder();
                String line = reader.readLine();
                while (line != null) {
                    builder.append(line);
                    line = reader.readLine();
                }
                historyString = builder.toString();

                Gson gson = new GsonBuilder().create();
                ArrayList<ServerCounters> c = gson.fromJson(historyString, new TypeToken<ArrayList<ServerCounters>>() {
                }.getType());
                counters.addAll(c);
            } finally {
                reader.close();
            }
        }catch(Exception e){
            e.printStackTrace();
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