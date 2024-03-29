package com.example.appProperties;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "plan-api")
@EnableConfigurationProperties
@Configuration
public class ApplicationProperties {

	Map<String, String> message = new HashMap<>();
}
