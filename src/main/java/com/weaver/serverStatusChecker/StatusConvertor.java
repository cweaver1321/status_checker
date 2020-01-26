package com.weaver.serverStatusChecker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * StatusConvertor will convert the status JSON into a POJO for others to use.
 * On error it currently will return a null. It is left to the caller how to
 * handle a null response.
 */
public class StatusConvertor {
    public static final Logger logger = LoggerFactory.getLogger(ServerStatusRunner.class);
    private final ObjectMapper mapper = new ObjectMapper();

    public StatusModel getStatusModel(String status){
        StatusModel statusModel = null;

        try {
            statusModel = mapper.readValue(status, StatusModel.class);
        } catch (JsonProcessingException e) {
            logger.error("JSON processing exception thrown with status of {}", status);
            e.printStackTrace();
        }

        return statusModel;
    }
}
