package com.ataccama.dbadger.config.ds;

import com.ataccama.dbadger.config.connection.DBConnectionContextHolder;
import com.ataccama.dbadger.config.connection.DBConnectionLog;
import com.ataccama.dbadger.factory.DataSourceFactory;
import com.ataccama.dbadger.service.connection.DBConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Component
public class DataSourceInterceptor implements HandlerInterceptor {
    private final DBConnectionService connectionService;
    private final DBConnectionLog connectionLog;
    private final DataSourceRouting dsRouting;
    private final DataSourceFactory dsFactory;

    @Autowired
    public DataSourceInterceptor(DBConnectionService connectionService, DBConnectionLog connectionLog, DataSourceRouting dsRouting, DataSourceFactory dsFactory) {
        this.connectionService = connectionService;
        this.connectionLog = connectionLog;
        this.dsRouting = dsRouting;
        this.dsFactory = dsFactory;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        var id = getId(request).orElseThrow(() -> new IllegalArgumentException("id not present in path"));
        var connection = connectionService.find(id);
        DBConnectionContextHolder.set(connection);

        var ds = dsRouting.getResolvedDataSources().get(connection);
        if (ds == null) {
            ds = dsFactory.create(connection);
            dsRouting.put(connection, ds);
        }
        connectionLog.put(connection);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        DBConnectionContextHolder.remove();
    }

    private Optional<Long> getId(HttpServletRequest request) {
        try {
            var attributes = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            var id = Long.parseLong(attributes.get("id"));
            return Optional.of(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
