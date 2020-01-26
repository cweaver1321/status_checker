package com.weaver.serverStatusChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Callable;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class StatusRetriever  implements Callable {
    public static final Logger logger = LoggerFactory.getLogger(StatusRetriever.class);
    String hostUrl;

    public StatusRetriever(String hostUrl) {
        this.hostUrl = hostUrl;
    }

    @Override
    public String call() {
        logger.debug("Calling host with url {} for status.", hostUrl);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(hostUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            logger.debug("Status received for {}", hostUrl);
            return response.body();
        }
        catch (IllegalArgumentException iae){
            logger.error("URI for host with url {} could not be created.", hostUrl, iae);
            return null;
        }
        catch (IOException|InterruptedException e){
            logger.error("Sending the request for url {} resulted in an error.", hostUrl, e);
            return null;
        }
    }
}
