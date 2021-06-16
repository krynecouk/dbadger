package com.ataccama.dbadger.service.connection;

import com.ataccama.dbadger.domain.DBConnection;
import com.ataccama.dbadger.repository.connection.DBConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsqlConnectionService implements DBConnectionService {

    private final DBConnectionRepository repository;

    @Autowired
    public PsqlConnectionService(DBConnectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DBConnection> findAll() {
        return repository.findAll();
    }

    @Override
    public void create(DBConnection connection) {
        repository.create(connection);
    }


}
