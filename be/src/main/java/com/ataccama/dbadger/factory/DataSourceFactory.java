package com.ataccama.dbadger.factory;

import com.ataccama.dbadger.domain.DBConnection;

import javax.sql.DataSource;

public interface DataSourceFactory {

    DataSource create(String url, String username, String password);

    DataSource create(DBConnection connection);

}
