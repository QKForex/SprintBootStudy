package com.lenovo.dpc.kafka;

import com.lenovo.dpc.config.DpcConfig;
import lombok.Getter;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
public class DpcKafkaConsumer {
    @Autowired
    @Getter
    private DpcConfig dpcConfig;

    @Bean(name = "MyKafkaConsumer")
    @Scope("prototype")
    public KafkaConsumer<String, String> kafkaConsumer() {
        System.out.println(dpcConfig.getAutoOffsetReset());
        return new KafkaConsumer<String, String>(dpcConfig.kafkaProperties());
    }
}
