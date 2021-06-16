package com.ataccama.dbadger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.datasource")
public record DataSourceConfigurationProperties(String url, String username, String password) {}