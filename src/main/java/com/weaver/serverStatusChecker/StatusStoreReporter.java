package com.weaver.serverStatusChecker;

import java.text.DecimalFormat;
import java.util.Collection;

/**
 * StatusStoreReport is responsible for creating the report for the StatusStore contents
 */
public class StatusStoreReporter {
    private static DecimalFormat df = new DecimalFormat("0.0");

    public String createReport(Collection<StatusModel> statusModels) {
        // start with new line and title
        StringBuffer sb = new StringBuffer("\nApplication,Version,Aggregated Success Rate\n");
        Double successRate = 0.0;

        for (StatusModel statusModel: statusModels) {
            successRate = 100 * (double)statusModel.getSuccess_count() / (double)statusModel.getRequests_count();
            sb.append(statusModel.getApplication() + " " +
                    statusModel.getVersion() + " " +
                    df.format(successRate) + "%\n");
        }
        return sb.toString();
    }
}
