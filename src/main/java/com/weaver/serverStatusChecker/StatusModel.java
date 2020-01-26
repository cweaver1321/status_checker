package com.weaver.serverStatusChecker;

/**
 * StatusModel is a POJO class for representing the server status
 */
public class StatusModel {
    private Long requests_count;
    private String application;
    private String version;
    private Long success_count;
    private Long error_count;

    public Long getRequests_count() {
        return requests_count;
    }

    public void setRequests_count(Long requests_count) {
        this.requests_count = requests_count;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getSuccess_count() {
        return success_count;
    }

    public void setSuccess_count(Long success_count) {
        this.success_count = success_count;
    }

    public Long getError_count() {
        return error_count;
    }

    public void setError_count(Long error_count) {
        this.error_count = error_count;
    }
}
