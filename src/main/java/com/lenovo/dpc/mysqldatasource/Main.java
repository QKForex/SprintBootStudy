package com.lenovo.dpc.mysqldatasource;

import com.lenovo.dpc.config.DpcConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DpcConfig.class);
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("MyJdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select distinct ctoCode from feature limit 10");
        for (Map<String, Object> map : list) {
            System.out.println(map.get("ctoCode"));
        }

    }
}
