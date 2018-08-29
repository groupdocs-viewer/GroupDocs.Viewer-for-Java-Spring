package com.groupdocs.ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:configuration.yml")
@ConfigurationProperties("application")
public class ApplicationConfiguration {

    @Value("${licensePath}")
    private String licensePath;

    public String getLicensePath() {
        return licensePath;
    }

    public void setLicensePath(String licensePath) {
        this.licensePath = licensePath;
    }

    @Override
    public String toString() {
        return "ApplicationConfiguration{" +
                "licensePath='" + licensePath + '\'' +
                '}';
    }
}
