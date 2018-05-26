package monitoring.monitors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import monitoring.DatabaseClient;
import monitoring.data.CollectionCounters;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import monitoring.model.DateValuePair;
import monitoring.model.Resource;
import monitoring.model.ResourceGetter;
import org.bson.Document;

import static monitoring.App.databaseConfig;

public class CollectionMonitor implements AbstractMonitor, Runnable{

    int period;
    int historySize;
    private DatabaseClient client;
    List<CollectionCounters> counters;
    private Date lastCountersReadTime;

    private String collectionName;

    public CollectionMonitor(int period, int historySize, String collectionName) {
        this.period = period;
        this.historySize = historySize;
        this.collectionName = collectionName;
        client = new DatabaseClient(databaseConfig);
        lastCountersReadTime = new Date();
        counters = new ArrayList<>();
        loadHistoryFromFile();
    }

    private void getNewCounters(){
        Document collectionCounters = client.getDatabase().runCommand(new Document("collStats",collectionName));
        CollectionCounters cc = new CollectionCounters(collectionName,new Date(),collectionCounters);
        if(counters.size()>historySize){
            counters.remove(0);
        }
        counters.add(cc);
        lastCountersReadTime = new Date();
    }

    public void run(){
        while(Thread.currentThread().isAlive()){
            Date currentDateTime = new Date();
            long diff = (currentDateTime.getTime() - lastCountersReadTime.getTime())/1000;
            if(diff>period){
                getNewCounters();
                saveToFile();
            }
            else{
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
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

    private void loadHistoryFromFile(){
        String historyString = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(collectionName + ".JSON"));
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();
            while(line!=null){
                builder.append(line);
                line = reader.readLine();
            }
            historyString = builder.toString();
            System.out.println(historyString);
            Gson gson = new GsonBuilder().create();
            ArrayList<CollectionCounters> c = gson.fromJson(historyString,new TypeToken<ArrayList<CollectionCounters>>(){}.getType());
            counters.addAll(c);
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    private void saveToFile(){
        try{
            Writer writer = new FileWriter(collectionName + ".JSON");
            Gson gson = new GsonBuilder().create();
            gson.toJson(counters, writer);
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
