package com.ataccama.dbadger.service.metadata;

import com.ataccama.dbadger.domain.DBColumn;
import com.ataccama.dbadger.domain.DBSchema;
import com.ataccama.dbadger.domain.DBTable;
import com.ataccama.dbadger.repository.metadata.DBMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PGMetadataService implements DBMetadataService {

    private final DBMetadataRepository repository;

    @Autowired
    public PGMetadataService(DBMetadataRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DBSchema> findAllSchemas() {
        return repository.findAllSchemas();
    }

    @Override
    public List<DBTable> findAllTables() {
        return repository.findAllTables();
    }

    @Override
    public List<DBColumn> findColumns(String tableName) {
        return repository.findColumns(tableName);
    }
}
