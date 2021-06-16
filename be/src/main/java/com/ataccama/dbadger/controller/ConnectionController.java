package com.ataccama.dbadger.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/connection", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ConnectionController {

    @Value("classpath:swagger/connection.yaml")
    Resource swagger;

    @GetMapping(path = "/docs")
    public ResponseEntity<Resource> getApiDocumentation() throws IOException {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(swagger.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(swagger);
    }
}
