package com.weaver.serverStatusChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * StatusStore is responsible for storing server
 * status values using the StatusModel
 */
public class StatusStore {
    public static final Logger logger = LoggerFactory.getLogger(StatusStore.class);
    private final Map<String, StatusModel> store;
    private final StatusStoreReporter statusStoreReporter;
    private final StatusAggregator statusAggregator;

    public StatusStore(StatusStoreReporter statusStoreReporter, StatusAggregator statusAggregator){
        this.store = new HashMap<String, StatusModel>();
        this.statusStoreReporter = statusStoreReporter;
        this.statusAggregator = statusAggregator;
    }

    public void upsert(StatusModel statusModel){
        String statusModelKey = statusModel.getApplication()+statusModel.getVersion();
        if(store.containsKey(statusModelKey)){
            //update
            StatusModel aggregate = statusAggregator.aggegate(statusModel, store.get(statusModelKey));
            store.put(statusModelKey, aggregate);
        }else{
            //insert
            store.put(statusModelKey, statusModel);
        }
    }

    public String report() {
        return statusStoreReporter.createReport(store.values());
    }
}
