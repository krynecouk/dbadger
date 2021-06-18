package com.ataccama.dbadger.service.data;

import java.util.List;
import java.util.Map;

public interface DBDataService {

    List<Map<String, String>> read(String tableName, Integer limit);

}
