package com.ataccama.dbadger.config.ds;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.datasource")
public record DataSourceProperties(String url, String username, String password) {
}