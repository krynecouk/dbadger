package com.ataccama.dbadger.service.connection;

import com.ataccama.dbadger.domain.DBConnection;

import java.util.List;

public interface DBConnectionService {

    List<DBConnection> findAll();

    DBConnection find(Long id);

    void create(DBConnection connection);

    void update(Long id, DBConnection connection);

    void remove(Long id);
}
