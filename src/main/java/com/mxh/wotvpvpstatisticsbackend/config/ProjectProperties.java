package com.mxh.wotvpvpstatisticsbackend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("project")
public class ProjectProperties {

    private String projectName;
    private Integer projectVersion;
}
