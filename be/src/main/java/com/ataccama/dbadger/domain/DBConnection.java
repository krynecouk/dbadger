package com.ataccama.dbadger.domain;

import java.util.Objects;

public record DBConnection(
        Long id,
        String name,
        String hostname,
        Integer port,
        String databaseName,
        String username,
        String password
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBConnection that = (DBConnection) o;
        return hostname.equals(that.hostname) && port.equals(that.port) && databaseName.equals(that.databaseName) && username.equals(that.username) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostname, port, databaseName, username, password);
    }
}

