package com.ataccama.dbadger.config.ds;

import com.ataccama.dbadger.factory.DataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class DataSourceConfig {

    private final DataSourceFactory dsFactory;

    @Autowired
    public DataSourceConfig(DataSourceFactory dsFactory) {
        this.dsFactory = dsFactory;
    }

    @Bean
    public DataSourceRouting dataSource(final DataSourceProperties props) {
        var routing = new DataSourceRouting();
        var ds = dsFactory.create(props.getUrl(), props.getUsername(), props.getPassword());
        routing.setDefaultTargetDataSource(ds);
        routing.setTargetDataSources(new HashMap<>());
        return routing;
    }
}
