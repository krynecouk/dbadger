package com.ataccama.dbadger.config.ds;

import com.ataccama.dbadger.domain.DBConnection;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;

public class DataSourceRouting extends AbstractRoutingDataSource {

    @Override
    protected DBConnection determineCurrentLookupKey() {
        return DBConnectionContextHolder.get();
    }

    synchronized public void put(DBConnection connection, DataSource ds) {
        var map = new HashMap<Object, Object>(this.getResolvedDataSources());
        map.put(connection, ds);
        this.setTargetDataSources(map);
        this.afterPropertiesSet();
    }

    synchronized protected void remove(DBConnection connection) {
        var map = new HashMap<Object, Object>(this.getResolvedDataSources());
        map.remove(connection);
        this.setTargetDataSources(map);
        this.afterPropertiesSet();
    }
}
