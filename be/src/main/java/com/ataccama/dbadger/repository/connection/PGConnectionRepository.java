package com.ataccama.dbadger.repository.connection;

import com.ataccama.dbadger.domain.DBConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class PGConnectionRepository implements DBConnectionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<DBConnection> mapper;

    @Autowired
    public PGConnectionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new DBConnectionMapper();
    }

    @Override
    public List<DBConnection> findAll() {
        var sql = "select * from connection";
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public Optional<DBConnection> find(long id) {
        var sql = "select * from connection where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, mapper, id));
    }

    @Override
    public void create(DBConnection connection) {
        var sql = """
                insert into connection values
                (?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(
                sql,
                connection.id(),
                connection.name(),
                connection.hostname(),
                connection.port(),
                connection.databaseName(),
                connection.username(),
                connection.password());
    }

    @Override
    public void update(long id, DBConnection connection) {
        var sql = """
                update connection set id = ?, name = ?, hostname = ?, port = ?, databaseName = ?, username = ?, password = ?
                where id = ?
                """;
        jdbcTemplate.update(
                sql,
                connection.id(),
                connection.name(),
                connection.hostname(),
                connection.port(),
                connection.databaseName(),
                connection.username(),
                connection.password(),
                connection.id()
        );
    }

    @Override
    public boolean remove(long id) {
        var sql = "delete from connection where id = ?";
        return jdbcTemplate.update(sql, id) == 1;
    }
}

class DBConnectionMapper implements RowMapper<DBConnection> {
    @Override
    public DBConnection mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DBConnection(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("hostname"),
                rs.getInt("port"),
                rs.getString("databaseName"),
                rs.getString("username"),
                rs.getString("password")
        );
    }
}
