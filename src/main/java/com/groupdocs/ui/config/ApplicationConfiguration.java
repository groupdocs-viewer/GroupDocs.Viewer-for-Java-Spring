package com.groupdocs.ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

import static com.groupdocs.ui.config.DefaultDirectories.defaultLicenseDirectory;
import static com.groupdocs.ui.config.DefaultDirectories.relativePathToAbsolute;

@Component
public class ApplicationConfiguration {

    @Value("${application.licensePath}")
    private String licensePath;

    @PostConstruct
    public void init() {
        this.licensePath = StringUtils.isEmpty(this.licensePath) ? defaultLicenseDirectory() : relativePathToAbsolute(this.licensePath);
    }

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
