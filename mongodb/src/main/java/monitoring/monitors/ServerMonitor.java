package monitoring.monitors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import monitoring.DatabaseClient;
import monitoring.data.ServerCounters;
import org.bson.Document;

import java.io.*;
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



    public List<ServerCounters> getCounters() {
        return counters;
    }

    public ServerMonitor(int period, int historySize) {
        this.period = period;
        this.historySize = historySize;
        this.counters = new ArrayList<>();
        client = new DatabaseClient(databaseConfig);
        lastCountersReadtime = new Date();
        loadHistoryToFile();
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

    private void loadHistoryToFile(){
        String historyString = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader("ServerCounters.JSON"));
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();
            while(line!=null){
                builder.append(line);
                line = reader.readLine();
            }
            historyString = builder.toString();

            Gson gson = new GsonBuilder().create();
            ArrayList<ServerCounters> c = gson.fromJson(historyString,new TypeToken<ArrayList<ServerCounters>>(){}.getType());
            counters.addAll(c);
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