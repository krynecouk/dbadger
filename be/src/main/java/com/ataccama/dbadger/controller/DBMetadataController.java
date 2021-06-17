package com.ataccama.dbadger.controller;

import com.ataccama.dbadger.domain.DBColumn;
import com.ataccama.dbadger.domain.DBSchema;
import com.ataccama.dbadger.domain.DBTable;
import com.ataccama.dbadger.service.metadata.DBMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost")
@RequestMapping(path = "/connections/{id}/metadata", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DBMetadataController {

    private final DBMetadataService metadataService;

    @Autowired
    public DBMetadataController(DBMetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @GetMapping(path = "/schemas")
    public List<DBSchema> getAllSchemas(@PathVariable Long id) {
        Assert.notNull(id, "id must not be null");
        return metadataService.findAllSchemas();
    }

    @GetMapping(path = "/tables")
    public List<DBTable> getAllTables(@PathVariable Long id) {
        Assert.notNull(id, "id must not be null");
        return metadataService.findAllTables();
    }

    @GetMapping(path = "/tables/{name}/columns")
    public List<DBColumn> getColumns(@PathVariable Long id, @PathVariable String name) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(name, "table name must not be null");
        return metadataService.findColumns(name);
    }
}