package com.groupdocs.ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfiguration {

    @Value("${application.licensePath}")
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
