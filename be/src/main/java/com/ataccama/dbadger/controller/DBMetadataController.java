package com.ataccama.dbadger.controller;

import com.ataccama.dbadger.domain.DBSchema;
import com.ataccama.dbadger.service.metadata.DBMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/connections/{id}/metadata", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DBMetadataController {

    private final DBMetadataService metadataService;

    @Value("classpath:swagger/metadata.yaml")
    Resource swagger;

    @Autowired
    public DBMetadataController(DBMetadataService metadataService) {
        this.metadataService = metadataService;
    }

    @GetMapping(path = "/schemas")
    public List<DBSchema> getAllSchemas() {
       return metadataService.findAllSchemas();
    }
}