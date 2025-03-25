package com.nt.commons;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;

@Configuration
@Data
@ConfigurationProperties(prefix="plan.module")
@EnableConfigurationProperties
public class AppConfigProperties {
private Map<String,String> messages;
}
