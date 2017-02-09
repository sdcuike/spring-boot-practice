package com.sdcuike.practice.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@ConfigurationProperties(prefix = "commonConfig")
@Getter
@Setter
@ToString
public class CommonConfig {

    @NotNull
    private String appName;

}
