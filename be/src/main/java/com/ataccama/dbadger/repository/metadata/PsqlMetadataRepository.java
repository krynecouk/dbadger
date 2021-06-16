package com.ataccama.dbadger.repository.metadata;

import com.ataccama.dbadger.domain.DBConnection;
import com.ataccama.dbadger.domain.DBSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PsqlMetadataRepository implements DBMetadataRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<DBSchema> schemaMapper;

    @Autowired
    public PsqlMetadataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.schemaMapper = new DBSchemaMapper();
    }

    @Override
    public List<DBSchema> findAllSchemas() {
        var sql = """
                SELECT schema_name, schema_owner
                FROM information_schema.schemata;
                """;
        return jdbcTemplate.query(sql, schemaMapper);
    }
}

class DBSchemaMapper implements RowMapper<DBSchema> {
    @Override
    public DBSchema mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DBSchema( rs.getString("name"), rs.getString("owner"));
    }
}
