package com.ataccama.dbadger.controller;


import com.ataccama.dbadger.service.data.DBDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost")
@RequestMapping(path = "/connections/{id}/data", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DBDataController {

    private final DBDataService dataService;

    @Autowired
    public DBDataController(DBDataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping(path = "/tables/{name}")
    public List<Map<String, String>> getAllSchemas(@PathVariable Long id, @PathVariable String name, @RequestParam(defaultValue = "10") Integer limit) {
        Assert.notNull(id, "id must not be null");
        Assert.notNull(id, "table name must not be null");
        return dataService.read(name, limit);
    }
}
