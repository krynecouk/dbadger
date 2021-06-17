package com.ataccama.dbadger.repository.metadata;

import com.ataccama.dbadger.domain.DBColumn;
import com.ataccama.dbadger.domain.DBSchema;
import com.ataccama.dbadger.domain.DBTable;

import java.util.List;

public interface DBMetadataRepository {

    List<DBSchema> findAllSchemas();

    List<DBTable> findAllTables();

    List<DBColumn> findColumns(String tableName);

}
