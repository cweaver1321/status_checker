package com.weaver.serverStatusChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ServerStatusRunner {
    public static final Logger logger = LoggerFactory.getLogger(ServerStatusRunner.class);

    public static void main(String[] args) {
        ServerStatusRunner serverStatusRunner = new ServerStatusRunner();
        if(args.length==1)
            logger.info(serverStatusRunner.getServerStatusReport(args[0], 50));
        else
            logger.info(serverStatusRunner.getServerStatusReport(null, 10));
    }

    public String getServerStatusReport(String prefix, Integer maxThreads){
        // TODO: read from file input but for now just create the url list
        // Create the ServerLoader and load our server urls.
        ServerLoader serverLoader = new ServerLoader(prefix);
        List<String> urls = serverLoader.getServers();

        // Create a maximum of maxThreads StatusRetrievers using an ExecutorService
        // to retrieve all statuses. This would need to be refactored for very large
        // lists of servers.
        ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);
        List<Future<String>> futures = new ArrayList<Future<String>>();
        for(int i = 0; i < urls.size(); i++)
            futures.add(executorService.submit(new StatusRetriever(urls.get(i))));
        executorService.shutdown();

        // Collect and convert the statuses from the futures and store them in the
        // StatusStore where they will be aggregated
        StatusConvertor convertor = new StatusConvertor();
        StatusStore store = new StatusStore(new StatusStoreReporter(), new StatusAggregator());
        StatusModel statusModel = null;
        for(Future<String> future: futures){
            try{
                statusModel = convertor.getStatusModel(future.get());
            } catch (InterruptedException|ExecutionException e) {
                logger.error("Exception occurred while getting the status from the future!");
            }
            if(statusModel!=null)
                store.upsert(statusModel);
        }

        return store.report();
    }
}
