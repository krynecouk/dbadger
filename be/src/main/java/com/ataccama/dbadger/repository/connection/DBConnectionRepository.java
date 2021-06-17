package com.ataccama.dbadger.repository.connection;

import com.ataccama.dbadger.domain.DBConnection;

import java.util.List;
import java.util.Optional;

public interface DBConnectionRepository {

    List<DBConnection> findAll();

    Optional<DBConnection> find(long id);

    void create(DBConnection connection);

    void update(long id, DBConnection connection);

    boolean remove(long id);
}
