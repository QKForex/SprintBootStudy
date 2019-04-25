package com.lenovo.dpc.kafka;

import com.lenovo.dpc.config.DpcConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(DpcConfig.class);
        context.refresh();
        DpcConfig dpcConfig = context.getBean(DpcConfig.class);
        DpcKafkaConsumer dpcKafkaConsumer = context.getBean(DpcKafkaConsumer.class);
        List<String> topicList = Arrays.asList(dpcConfig.getTopicList().split(","));
        List<Thread> topicConsumersThreads = topicList.stream().map(topic -> new Thread(new TopicConsumer(topic, dpcConfig, dpcKafkaConsumer))).collect(Collectors.toList());
        topicConsumersThreads.stream().forEach(Thread::start);
    }
}
