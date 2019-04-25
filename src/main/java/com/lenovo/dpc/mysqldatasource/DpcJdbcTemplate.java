package com.lenovo.dpc.mysqldatasource;

import com.lenovo.dpc.config.DpcConfig;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class DpcJdbcTemplate {

    @Autowired
    private DpcConfig dpcConfig;

    @Bean(name = "MyJdbcTemplate")
    public org.springframework.jdbc.core.JdbcTemplate JdbcTemplate() {
        return new org.springframework.jdbc.core.JdbcTemplate(dataSource());
    }

    private DataSource dataSource() {
        PoolProperties poolConfig = new PoolProperties();
        poolConfig.setUrl(dpcConfig.getJdbcUrl());
        poolConfig.setUsername(dpcConfig.getJdbcUser());
        poolConfig.setPassword(dpcConfig.getJdbcPasswd());
        poolConfig.setDriverClassName(dpcConfig.getDriverClass());
        poolConfig.setInitialSize(dpcConfig.getJdbcPoolInitialSize());
        poolConfig.setMaxActive(dpcConfig.getJdbcPoolMaxActive());
        poolConfig.setMaxIdle(dpcConfig.getJdbcPoolMaxIdle());
        poolConfig.setMaxWait(dpcConfig.getJdbcPoolMaxWait());
        poolConfig.setTestOnBorrow(true);
        poolConfig.setValidationQuery(DatabaseDriver.MYSQL.getValidationQuery());
        poolConfig.setTestWhileIdle(true);
        poolConfig.setTimeBetweenEvictionRunsMillis(1000 * 5);
        return new org.apache.tomcat.jdbc.pool.DataSource(poolConfig);
    }
}
