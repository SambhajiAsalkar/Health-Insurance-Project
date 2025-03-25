package com.bm.commons;

import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
@Data
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "plan.module")
@Component
public class AppConfig
{
Map<String ,String > messages;
}
