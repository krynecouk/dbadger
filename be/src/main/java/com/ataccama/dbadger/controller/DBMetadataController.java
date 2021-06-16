package com.ataccama.dbadger.controller;

import com.ataccama.dbadger.domain.DBConnection;
import com.ataccama.dbadger.domain.DBSchema;
import com.ataccama.dbadger.service.connection.DBConnectionService;
import com.ataccama.dbadger.service.metadata.DBMetadataService;
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
@RequestMapping(path = "/metadata", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DBMetadataController {

    private final DBMetadataService service;

    @Value("classpath:swagger/metadata.yaml")
    Resource swagger;

    @Autowired
    public DBMetadataController(DBMetadataService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}/schema")
    public List<DBSchema> getAllSchemas(@PathVariable String id) {
       return service.findAllSchemas();
    }

    @GetMapping(path = "/docs")
    public ResponseEntity<Resource> getApiDocumentation() throws IOException {
        return ResponseEntity.ok()
                .contentLength(swagger.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(swagger);
    }
}
