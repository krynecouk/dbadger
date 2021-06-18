package com.ataccama.dbadger.config.connection;

import com.ataccama.dbadger.domain.DBConnection;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DBConnectionLog {

    private final Map<DBConnection, LocalDateTime> connections;

    public DBConnectionLog() {
        this.connections = new ConcurrentHashMap<>();
    }

    public Map<DBConnection, LocalDateTime> getConnections() {
        return connections;
    }

    public void put(DBConnection connection) {
        this.connections.put(connection, LocalDateTime.now());
    }

    public void remove(DBConnection connection) {
        this.connections.remove(connection);
    }
}
