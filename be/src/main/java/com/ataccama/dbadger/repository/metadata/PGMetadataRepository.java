package com.ataccama.dbadger.repository.metadata;

import com.ataccama.dbadger.domain.DBColumn;
import com.ataccama.dbadger.domain.DBSchema;
import com.ataccama.dbadger.domain.DBTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PGMetadataRepository implements DBMetadataRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<DBSchema> schemaMapper;
    private final RowMapper<DBTable> tableMapper;
    private final RowMapper<DBColumn> columnMapper;

    @Autowired
    public PGMetadataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.schemaMapper = (rs, rowNum) -> new DBSchema(rs.getString("schema_name"), rs.getString("schema_owner"));
        this.tableMapper = (rs, rowNum) -> new DBTable(rs.getString("tablename"), rs.getString("tableowner"));
        this.columnMapper = (rs, rowNum) -> new DBColumn(
                rs.getString("column_name"),
                rs.getInt("ordinal_position"),
                "YES".equals(rs.getString("is_nullable"))
        );
    }

    @Override
    public List<DBSchema> findAllSchemas() {
        var sql = """
                SELECT schema_name, schema_owner
                FROM information_schema.schemata;
                """;
        return jdbcTemplate.query(sql, schemaMapper);
    }

    @Override
    public List<DBTable> findAllTables() {
        var sql = """
                SELECT tablename, tableowner
                FROM pg_catalog.pg_tables
                WHERE schemaname != 'pg_catalog' AND
                        schemaname != 'information_schema';
                  """;
        return jdbcTemplate.query(sql, tableMapper);
    }

    @Override
    public List<DBColumn> findColumns(String tableName) {
        var sql = """
                SELECT column_name, ordinal_position, is_nullable
                FROM information_schema.columns
                WHERE table_name = ?
                  """;
        return jdbcTemplate.query(sql, columnMapper, tableName);
    }
}
