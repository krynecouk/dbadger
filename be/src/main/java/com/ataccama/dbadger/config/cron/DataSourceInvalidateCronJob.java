package com.ataccama.dbadger.config.cron;

import com.ataccama.dbadger.config.connection.DBConnectionLog;
import com.ataccama.dbadger.config.ds.DataSourceRouting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataSourceInvalidateCronJob {
    private final DBConnectionLog connectionLog;
    private final DataSourceRouting routing;

    @Autowired
    public DataSourceInvalidateCronJob(DBConnectionLog connectionLog, DataSourceRouting routing) {
        this.connectionLog = connectionLog;
        this.routing = routing;
    }

    // TODO should be much more configurable and smarter (sync?)
    @Scheduled(cron = "0 0 * * *")
    public void invalidateOldDataSources() {
        var old = LocalDateTime.now().minusDays(2);
        connectionLog.getConnections().forEach((connection, lastTimeAlive) -> {
            if (lastTimeAlive.isBefore(old)) {
                routing.remove(connection);
                connectionLog.remove(connection);
            }
        });
    }
}