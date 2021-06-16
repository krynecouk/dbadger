package com.ataccama.dbadger.repository.metadata;

import com.ataccama.dbadger.domain.DBConnection;
import com.ataccama.dbadger.domain.DBSchema;

import java.util.List;
import java.util.Optional;

public interface DBMetadataRepository {

    List<DBSchema> findAllSchemas();

}
