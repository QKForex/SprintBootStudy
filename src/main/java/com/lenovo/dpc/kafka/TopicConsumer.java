package com.lenovo.dpc.kafka;

import com.lenovo.dpc.config.DpcConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.internals.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class TopicConsumer implements Runnable {

    private String topic;
    private KafkaConsumer<String, String> consumer;

    private DpcKafkaConsumer dpcKafkaConsumer;

    private DpcConfig dpcConfig;

    @Override
    public void run() {
        System.out.println("run here ");
        cache2File_Split_By_Partition();
    }

    public TopicConsumer(String topic, DpcConfig dpcConfig, DpcKafkaConsumer dpcKafkaConsumer) {
        this.dpcConfig = dpcConfig;
        this.dpcKafkaConsumer = dpcKafkaConsumer;
        this.topic = topic;
        this.consumer = this.dpcKafkaConsumer.kafkaConsumer();
        consumer.subscribe(Arrays.asList(this.topic), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                //consumer.seekToBeginning(collection);
            }
        });
    }

    private void loopRecords(ConsumerRecords<String, String> records, Map<Integer, LinkedList<String>> partitionMap, Map<Integer, Long> offsetsMap) {
        //循环每个分区
        for (TopicPartition partition : records.partitions()) {
            //0 1 2当前分区的编号
            int partitionNum = partition.partition();
            //循环每个分区的记录
            List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
            //循环记录每一条记录
            for (ConsumerRecord<String, String> record : partitionRecords) {
                System.out.println(record.value());
                //把记录存在list中
                partitionMap.get(partitionNum).addLast(record.key() + "!!!!!" + record.offset() + "!!!!!" + record.value());
            }
            if (partitionMap.get(partitionNum).size() > 0) {
                long offset = Long.valueOf(Arrays.asList(partitionMap.get(partitionNum).getLast().split("!!!!!")).get(1));
                //log.info(partitionNum + " partition's offset is " + offset);
                //获取当前分区的当前有效的offset值
                offsetsMap.put(partitionNum, offset);
            }
        }
    }

    private void cache2File_Split_By_Partition() {
        //获取topic的分区个数
        //log.info("Crrent Thread is " + Thread.currentThread().getName());
        //System.out.println("Crrent Thread is " + Thread.currentThread().getName());
        int partitions = this.consumer.partitionsFor(this.topic).size();
        //long starttime = System.currentTimeMillis();
        Integer batchSize = this.dpcConfig.getBatchsize();
        // System.out.println(batchSize);
        String cacheFilePath = this.dpcConfig.getCacheFilePath().trim();
        Long waitSecond = this.dpcConfig.getWaitsecond();
        //System.out.println(waitSecond);
        //System.exit(1);
        Map<Integer, LinkedList<String>> partitionMap = new HashMap<Integer, LinkedList<String>>();
        Map<Integer, Long> offsetsMap = new HashMap<Integer, Long>();
        Map<Integer, Boolean> commitFlagMap = new HashMap<Integer, Boolean>();
        IntStream.range(0, partitions).boxed().forEach(partitionNum -> {
            System.out.println("here");
            partitionMap.put(partitionNum, new LinkedList<>());
            offsetsMap.put(partitionNum, 0L);
            commitFlagMap.put(partitionNum, false);
        });
        this.consumer.subscribe(Arrays.asList(this.topic), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                //consumer.seekToBeginning(collection);
                //getConsumer().seek();
            }
        });
        while (true) {
            //等待5秒，如果没有获取到记录，则返回一个空集合
            // System.out.println("haha");
            //System.out.println(this.consumer);
            //System.out.println(this.dpcConfig.getMaxPollRecords());
            ConsumerRecords<String, String> records = this.consumer.poll(Duration.ofSeconds(waitSecond));
            //System.out.println(records);
            //System.out.println("hello");
            //如果没抽取到数据，不会走下面的for 循环
            loopRecords(records, partitionMap, offsetsMap);
            int size = IntStream.range(0, partitions).map(i -> partitionMap.get(i).size()).sum();
            if (size >= batchSize || (records.isEmpty() && size > 0)) {
                IntStream.range(0, partitions).forEach(i -> {
                    try {
                        String fileName = cacheFilePath + "/" + this.topic + "_" + i + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()) + ".txt";
                        File file = new File(fileName);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        //log.info("Generate new file  " + fileName);
                        FileWriter fileWriter = new FileWriter(fileName);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        for (String strs : partitionMap.get(i)) {
                            bufferedWriter.write(strs + System.lineSeparator());
                            //System.out.println(strs);
                        }
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        if (file.length() == 0) {
                            file.delete();
                        }
                        commitFlagMap.put(i, true);
                        //System.out.println(commitFlagMap.get(i));
                        if (commitFlagMap.get(i)) {

                            this.consumer.commitSync(Collections.singletonMap(new TopicPartition(this.topic, i), new OffsetAndMetadata(offsetsMap.get(i) + 1)));
                            commitFlagMap.put(i, false);
                        }
                        partitionMap.get(i).clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                   // log.info(offsetsMap.toString());
                });
            }
        }
    }
}




