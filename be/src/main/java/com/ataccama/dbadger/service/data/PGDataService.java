package com.ataccama.dbadger.service.data;

import com.ataccama.dbadger.repository.data.DBDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PGDataService implements DBDataService {

    private final DBDataRepository dataRepository;

    @Autowired
    public PGDataService(DBDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public List<Map<String, String>> read(String tableName, Integer limit) {
        return dataRepository.read(tableName, limit);
    }
}