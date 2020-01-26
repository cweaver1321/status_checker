package com.weaver.serverStatusChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * ServerLoader will load the servers from a file and provide them as a list
 */
public class ServerLoader {
    private List<String> servers = new ArrayList<String>();

    // TODO: replace with file loading
    public ServerLoader(){
        servers.add("http://localhost:8000/fictitiousServer/hosts/host0/status");
        servers.add("http://localhost:8000/fictitiousServer/hosts/host1/status");
        servers.add("http://localhost:8000/fictitiousServer/hosts/host2/status");
        servers.add("http://localhost:8000/fictitiousServer/hosts/host3/status");
        servers.add("http://localhost:8000/fictitiousServer/hosts/host4/status");
    }

    public ServerLoader(String hostPrefix) {
        if (hostPrefix == null) {
            hostPrefix = "http://localhost:8000/fictitiousServer/hosts/host";
            for (int i = 0; i < 10; i++) {
                servers.add(hostPrefix + i + "/status");
            }
        } else {
            for (int i = 0; i < 1000; i++) {
                servers.add(hostPrefix + i + "/status");
            }
        }
    }

    public List<String> getServers(){
        return servers;
    };
}
