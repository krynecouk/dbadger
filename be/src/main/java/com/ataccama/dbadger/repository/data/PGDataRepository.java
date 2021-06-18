package com.ataccama.dbadger.repository.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PGDataRepository implements DBDataRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Map<String, String>> mapper;

    @Autowired
    public PGDataRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = new MapMapper();
    }

    @Override
    public List<Map<String, String>> read(String tableName, Integer limit) {
        var sql = "select * from " + tableName + "  limit ?";
        return jdbcTemplate.query(sql, mapper, limit);
    }
}

class MapMapper implements RowMapper<Map<String, String>> {
    @Override
    public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Map<String, String> map = new HashMap<>();
        final ResultSetMetaData metadata = rs.getMetaData();
        for (int i = 1; i <= metadata.getColumnCount(); i++) {
            String key = metadata.getColumnLabel(i);
            String value = rs.getString(i);
            map.put(key, value);
        }
        return map;
    }
}
