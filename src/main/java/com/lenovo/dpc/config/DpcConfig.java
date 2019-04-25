package com.lenovo.dpc.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Properties;

@Configuration
@ComponentScan("com.lenovo.dpc")
//@PropertySource("classpath:application.properties")
public class DpcConfig {
    @Value("${kafka.botstrapServers}")
    private String botstrapServers;
    @Value("${kafka.groupId}")
    private String groupId;
    @Value("${kafka.enableAutoCommit}")
    private Boolean enableAutoCommit;
    @Value("${kafka.autoOffsetReset}")
    @Getter
    private String autoOffsetReset;
    @Value("${kafka.maxPollRecords}")
    @Getter
    private Integer maxPollRecords;


    @Value("${kafka.keyDeserializer}")
    private String keyDeserializer;
    @Value("${kafka.valueDeserializer}")
    private String valueDeserializer;
    @Value("${kafka.batchsize}")
    @Getter
    private Integer batchsize;
    @Value("${kafka.waitsecond}")
    @Getter
    private Long waitsecond;
    @Value("${kafka.topicList}")
    @Getter
    private String topicList;
    @Value("${kafka.resetOffsetList}")
    @Getter
    private String resetOffsetList;
    @Value("${kafka.cacheFilePath}")
    @Getter
    private String cacheFilePath;

    @Value("${datasource.jdbcUrl}")
    @Getter
    private String jdbcUrl;

    @Value("${datasource.driverClass}")
    @Getter
    private String driverClass;

    @Value("${datasource.jdbcUser}")
    @Getter
    private String jdbcUser;

    @Value("${datasource.jdbcPasswd}")
    @Getter
    private String jdbcPasswd;

    @Value("${datasource.jdbcPoolInitialSize}")
    @Getter
    private Integer jdbcPoolInitialSize;

    @Value("${datasource.jdbcPoolMaxIdle}")
    @Getter
    private Integer jdbcPoolMaxIdle;

    @Value("${datasource.jdbcPoolMaxActive}")
    @Getter
    private Integer jdbcPoolMaxActive;

    @Value("${datasource.jdbcPoolMaxWait}")
    @Getter
    private Integer jdbcPoolMaxWait;

    @Value("${dpc.threadpool.corePoolSize}")
    @Getter
    private Integer corePoolSize;

    @Value("${dpc.threadpool.maxPoolSize}")
    @Getter
    private Integer maxPoolSize;

    @Value("${dpc.threadpool.keepAliveTime}")
    @Getter
    private Integer keepAliveTime;

    //@Bean
    public Properties kafkaProperties() {
        Properties config = new Properties();
        config.put("bootstrap.servers", botstrapServers);
        config.put("enable.auto.commit", enableAutoCommit);
        config.put("auto.offset.reset", autoOffsetReset);
        config.put("key.deserializer", keyDeserializer);
        config.put("max.pool.records", maxPollRecords);
        config.put("value.deserializer", valueDeserializer);
        config.put("group.id", groupId);
        return config;
    }


}
