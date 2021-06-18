package com.ataccama.dbadger.repository.data;


import java.util.List;
import java.util.Map;

public interface DBDataRepository {

    List<Map<String, String>> read(String tableName, Integer limit);

}
