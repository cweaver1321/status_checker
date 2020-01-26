package com.weaver.serverStatusChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * StatusAggregator is used to aggregate 2 StatusModels that have the same application and version
 */
public class StatusAggregator {
    public static final Logger logger = LoggerFactory.getLogger(StatusAggregator.class);

    public StatusModel aggegate(StatusModel statusModel1, StatusModel statusModel2) {
        validate(statusModel1, statusModel2);

        StatusModel aggregate = new StatusModel();
        aggregate.setApplication(statusModel1.getApplication());
        aggregate.setVersion(statusModel1.getVersion());
        aggregate.setRequests_count(statusModel1.getRequests_count()+statusModel2.getRequests_count());
        aggregate.setSuccess_count(statusModel1.getSuccess_count()+statusModel2.getSuccess_count());
        aggregate.setError_count(statusModel1.getError_count()+statusModel2.getError_count());

        return aggregate;
    }

    protected void validate(StatusModel statusModel1, StatusModel statusModel2) {
        if(!statusModel1.getApplication().equals(statusModel2.getApplication())) {
            logger.error("StatusAggregator Misuse: Aggregated StatusModels must have the same application {}!={}!", statusModel1.getVersion(), statusModel2.getVersion());
            throw new IllegalArgumentException("StatusAggregator Misuse: Aggregated StatusModels must have the same application!");
        } else if(!statusModel1.getVersion().equals(statusModel2.getVersion())) {
            logger.error("StatusAggregator Misuse: Aggregated StatusModels must have the same version {}!={}!", statusModel1.getVersion(), statusModel2.getVersion());
            throw new IllegalArgumentException("StatusAggregator Misuse: Aggregated StatusModels must have the same version!");
        }

    }
}
