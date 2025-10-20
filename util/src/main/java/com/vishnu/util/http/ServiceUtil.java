package com.vishnu.util.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Slf4j
public class ServiceUtil {

    private final String port;
    private String serviceAddress;

    public ServiceUtil(@Value("${server.port}") String port) {
        this.port = port;
    }

    public String serviceAddress() {
        if (serviceAddress == null) {
            serviceAddress = "%s/%s:%s".formatted(findMyHostname(), findMyIpAddress(), port);
        }
        return serviceAddress;
    }

    private String findMyHostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            log.warn("Failed to determine hostname", e);
            return "unknown-host";
        }
    }

    private String findMyIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.warn("Failed to determine IP address", e);
            return "unknown-ip";
        }
    }
}
