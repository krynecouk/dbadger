package com.ataccama.dbadger.service.metadata;

import com.ataccama.dbadger.domain.DBConnection;
import com.ataccama.dbadger.domain.DBSchema;

import java.util.List;

public interface DBMetadataService {
    List<DBSchema> findAllSchemas();
}
