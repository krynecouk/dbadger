package com.ataccama.dbadger.factory;

import com.ataccama.dbadger.domain.DBConnection;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class PGDataSourceFactory implements DataSourceFactory {

    @Override
    public DataSource create(String url, String username, String password) {
        return DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }

    @Override
    public DataSource create(DBConnection connection) {
        var url = "jdbc:postgresql://" + connection.hostname() + ":" + connection.port() + "/" + connection.databaseName();
        return create(url, connection.username(), connection.password());
    }

}
