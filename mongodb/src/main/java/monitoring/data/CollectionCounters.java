package monitoring.data;

import org.bson.Document;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class CollectionCounters {
    private String collectionName;
    private Date timeStamp;
    private int size;
    private int count;
    private int avgObjSize;
    private int storageSize;
    private int nindexes;
    private Map<String,String> indexes;
    private int totalIndexSize;
    private int transactionUpdateConflict;


    @Override
    public String toString() {
        return "CollectionCounters{" +
                "collectionName='" + collectionName + '\'' +
                ", timeStamp=" + timeStamp +
                ", size=" + size +
                ", count=" + count +
                ", avgObjSize=" + avgObjSize +
                ", storageSize=" + storageSize +
                ", nindexes=" + nindexes +
                ", indexes=" + indexes +
                ", totalIndexSize=" + totalIndexSize +
                ", transactionUpdateConflict=" + transactionUpdateConflict +
                '}';
    }

    public Map<String, String> getIndexes() {
        return indexes;
    }

    public void setIndexes(Map<String, String> indexes) {
        this.indexes = indexes;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getAvgObjSize() {
        return avgObjSize;
    }

    public void setAvgObjSize(int avgObjSize) {
        this.avgObjSize = avgObjSize;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(int storageSize) {
        this.storageSize = storageSize;
    }

    public int getNindexes() {
        return nindexes;
    }

    public void setNindexes(int nindexes) {
        this.nindexes = nindexes;
    }

    public int getTotalIndexSize() {
        return totalIndexSize;
    }

    public void setTotalIndexSize(int totalIndexSize) {
        this.totalIndexSize = totalIndexSize;
    }

    public int getTransactionUpdateConflict() {
        return transactionUpdateConflict;
    }

    public void setTransactionUpdateConflict(int transactionUpdateConflict) {
        this.transactionUpdateConflict = transactionUpdateConflict;
    }


    public CollectionCounters(String collectionName, Date timeStamp, Document document) {
        indexes = new Hashtable<String, String>();
        this.collectionName = collectionName;
        this.timeStamp = timeStamp;
        this.size = document.getInteger("size");
        this.count = document.getInteger("count");
        this.avgObjSize = document.getInteger("avgObjSize");
        this.storageSize = document.getInteger("storageSize");
        this.nindexes = document.getInteger("nindexes");
        this.totalIndexSize = document.getInteger("totalIndexSize");
        if(nindexes>0){
            Document doc = (Document)document.get("indexSizes");
            for (String key:doc.keySet()
                 ) {
                indexes.put(key,doc.getInteger(key).toString());
            }
        }
    }









}
