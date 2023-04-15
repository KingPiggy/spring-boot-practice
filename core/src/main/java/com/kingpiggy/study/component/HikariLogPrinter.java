package com.kingpiggy.study.component;

import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * HikariLogPrinter class.
 * <PRE>
 * Describe here.
 * </PRE>
 *
 * <PRE>
 * <B>History:</B>
 * SeungHoon Lee, 0.1.0, Created at 2023.03.19
 * </PRE>
 *
 * @author : SEUNGHOON
 * @version : 0.1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class HikariLogPrinter {

    private final JdbcTemplate jdbcTemplate;

    public void printHikariLog() {
        HikariDataSource dataSource = (HikariDataSource) jdbcTemplate.getDataSource();
        HikariConfigMXBean config = dataSource.getHikariConfigMXBean();
        HikariPoolMXBean pool =  dataSource.getHikariPoolMXBean();

        log.info("[Hikari Config Log]");
        log.info("Connection time out : {}", config.getConnectionTimeout());
        log.info("Idle time out : {}", config.getIdleTimeout());
        log.info("Max life time : {}", config.getMaxLifetime());

        log.info("[Hikari Pool Log]");
        log.info("Active connections : {}", pool.getActiveConnections());
        log.info("Idle connections : {}", pool.getIdleConnections());
        log.info("All connections : {}", pool.getTotalConnections());
    }

}
