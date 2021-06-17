package com.ataccama.dbadger.service.connection;

import com.ataccama.dbadger.domain.DBConnection;
import com.ataccama.dbadger.exception.NotFoundException;
import com.ataccama.dbadger.repository.connection.DBConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PGConnectionService implements DBConnectionService {

    private final DBConnectionRepository repository;

    @Autowired
    public PGConnectionService(DBConnectionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DBConnection> findAll() {
        return repository.findAll();
    }

    @Override
    public DBConnection find(Long id) {
        return repository.find(id).orElseThrow(() -> new NotFoundException(id.toString(), "connection"));
    }

    @Override
    public void create(DBConnection connection) {
        repository.create(connection);
    }

    @Override
    public void update(Long id, DBConnection connection) {
        repository.update(id, connection);
    }

    @Override
    public void remove(Long id) {
        repository.remove(id);
    }
}