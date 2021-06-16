package com.ataccama.dbadger.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

public class DataSourceRouting extends AbstractRoutingDataSource {

    @Override
    protected String determineCurrentLookupKey() {
        return SessionIdContextHolder.get();
    }

    protected void put(String sessionId, DataSource ds) {
        var map = new ConcurrentHashMap<Object, Object>(this.getResolvedDataSources());
        map.put(sessionId, ds);
        this.setTargetDataSources(map);
    }
    
    protected void remove(String sessionId) {
        var map = new ConcurrentHashMap<Object, Object>(this.getResolvedDataSources());
        map.remove(sessionId);
        this.setTargetDataSources(map);
    }
}
