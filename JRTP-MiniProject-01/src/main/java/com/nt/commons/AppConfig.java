package com.nt.commons;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix="plan.module")
@Data
public class AppConfig 
{
	private Map<String,String> messages;

}
