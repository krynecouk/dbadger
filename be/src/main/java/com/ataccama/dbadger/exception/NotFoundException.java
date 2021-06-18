package com.ataccama.dbadger.exception;

import static java.lang.String.format;

public class NotFoundException extends RuntimeException {
    private final String id;
    private final String type;

    public NotFoundException(String id, String type) {
        super(format("object of type %s and id %s was not found", type, id));
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}