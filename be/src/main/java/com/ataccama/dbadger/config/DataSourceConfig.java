package com.ataccama.dbadger.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource(final DataSourceProperties props) {
        var routing = new DataSourceRouting();
        var ds = DataSourceBuilder.create()
                .url(props.getUrl())
                .username(props.getUsername())
                .password(props.getPassword())
                .build();
        routing.setDefaultTargetDataSource(ds);
        routing.setTargetDataSources(new ConcurrentHashMap<>());
        return routing;
    }
}
