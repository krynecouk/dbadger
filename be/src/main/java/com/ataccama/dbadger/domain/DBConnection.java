package com.ataccama.dbadger.domain;

public record DBConnection(
        Long id,
        String name,
        String hostname,
        int port,
        String databaseName,
        String username,
        String password) {};