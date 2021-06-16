package com.ataccama.dbadger.controller;

import com.ataccama.dbadger.domain.DBConnection;
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
@RequestMapping(path = "/connection", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DBConnectionController {

    private final DBConnectionService service;

    @Value("classpath:swagger/connection.yaml")
    Resource swagger;

    @Autowired
    public DBConnectionController(DBConnectionService service) {
        this.service = service;
    }

    @GetMapping
    public List<DBConnection> getAll() {
       return service.findAll();
    }

    @PostMapping
    public void create(@RequestBody DBConnection connection) {
        service.create(connection);
    }

    @GetMapping(path = "/docs")
    public ResponseEntity<Resource> getApiDocumentation() throws IOException {
        return ResponseEntity.ok()
                .contentLength(swagger.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(swagger);
    }
}
