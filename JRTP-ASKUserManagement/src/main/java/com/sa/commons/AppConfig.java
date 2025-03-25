package com.sa.commons;

import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



import lombok.Data;
@Data
@EnableAutoConfiguration
@Configuration
@ConfigurationProperties(prefix ="user.module")
public class AppConfig {
  Map<String,String> messages;

}
