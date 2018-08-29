package com.groupdocs.ui.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ServerConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(ServerConfiguration.class);

    private int httpPort;
    private String hostAddress;

    @PostConstruct
    public void init() {
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("Can not get host address ", e);
            hostAddress = "localhost";
        }
    }

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    @Override
    public String toString() {
        return "ServerConfiguration{" +
                "httpPort=" + httpPort +
                ", hostAddress='" + hostAddress + '\'' +
                '}';
    }
}
