package com.ataccama.dbadger.service.metadata;

import com.ataccama.dbadger.domain.DBConnection;
import com.ataccama.dbadger.domain.DBSchema;
import com.ataccama.dbadger.repository.connection.DBConnectionRepository;
import com.ataccama.dbadger.repository.metadata.DBMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PsqlMetadataService implements DBMetadataService {

    private final DBMetadataRepository repository;

    @Autowired
    public PsqlMetadataService(DBMetadataRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<DBSchema> findAllSchemas() {
        return repository.findAllSchemas();
    }
}
