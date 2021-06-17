package com.ataccama.dbadger.controller;

import com.ataccama.dbadger.domain.DBConnection;
import com.ataccama.dbadger.service.connection.DBConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost")
@RequestMapping(path = "/connections", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DBConnectionController {

    private final DBConnectionService connectionService;

    @Value("classpath:swagger/api-docs.yaml")
    Resource swagger;

    @Autowired
    public DBConnectionController(DBConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @GetMapping
    public List<DBConnection> findAll() {
       return connectionService.findAll();
    }

    @GetMapping(path = "/{id}")
    public DBConnection find(@PathVariable Long id) {
        Assert.notNull(id, "id must not be null");
        return connectionService.find(id);
    }

    @PutMapping
    public void update(@RequestBody DBConnection connection) {
        assertNotNull(connection);
        connectionService.create(connection);
    }

    @PostMapping
    public void create(@RequestBody DBConnection connection) {
        assertNotNull(connection);
        connectionService.create(connection);
    }

    @DeleteMapping(path = "/{id}")
    public void remove(@PathVariable Long id) {
        connectionService.remove(id);
    }

    @GetMapping(path = "/docs")
    public ResponseEntity<Resource> getApiDocumentation() throws IOException {
        return ResponseEntity.ok()
                .contentLength(swagger.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(swagger);
    }

    private void assertNotNull(DBConnection connection) {
        Assert.notNull(connection, "connection must not be null");
        Assert.notNull(connection.id(), "connection id not be null"); // TODO should be serial
        Assert.notNull(connection.hostname(), "hostname must not be null");
        Assert.notNull(connection.port(), "port must not be null");
        Assert.notNull(connection.databaseName(), "database name must not be null");
        Assert.notNull(connection.username(), "username must not be null");
        Assert.notNull(connection.password(), "password must not be null");
    }
}
