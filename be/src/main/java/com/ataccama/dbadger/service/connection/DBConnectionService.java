package com.ataccama.dbadger.service.connection;

import com.ataccama.dbadger.domain.DBConnection;

import java.util.List;

public interface DBConnectionService {
    List<DBConnection> findAll();
    void create(DBConnection connection);
}
