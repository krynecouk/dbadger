package com.ataccama.dbadger.controller;

import com.ataccama.dbadger.domain.DBConnection;
import com.ataccama.dbadger.domain.DBSchema;
import com.ataccama.dbadger.service.connection.DBConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public List<DBConnection> getAllConnections() {
       return connectionService.findAll();
    }

    @PostMapping
    public void createConnection(@RequestBody DBConnection connection) {
        connectionService.create(connection);
    }

    @GetMapping(path = "/docs")
    public ResponseEntity<Resource> getApiDocumentation() throws IOException {
        return ResponseEntity.ok()
                .contentLength(swagger.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(swagger);
    }
}
