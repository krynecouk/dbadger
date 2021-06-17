package com.ataccama.dbadger.config.ds;

import com.ataccama.dbadger.domain.DBConnection;

public class DBConnectionContextHolder {
    private static final ThreadLocal<DBConnection> DB_CONNECTION = new ThreadLocal<>();

    public static void set(final DBConnection connection) {
        DB_CONNECTION.set(connection);
    }

    public static DBConnection get() {
        return DB_CONNECTION.get();
    }

    public static void remove() {
        DB_CONNECTION.remove();
    }
}
