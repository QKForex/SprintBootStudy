package com.lenovo.dpc.kafka;

import com.lenovo.dpc.config.DpcConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ResetOffsetByPartition {

    /*@Autowired
    private static DpcConfig dpcConfig;
*/
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DpcConfig.class);
        DpcKafkaConsumer dpcKafkaConsumer = context.getBean(DpcKafkaConsumer.class);
        KafkaConsumer consumer = dpcKafkaConsumer.kafkaConsumer();
        DpcConfig dpcConfig = context.getBean(DpcConfig.class);

        //Cong配置文件中获取每个需要重置的Topic的Partition的Offset信息
        String resetOffsetList = dpcConfig.getResetOffsetList().trim();
        //定义一个List用来存储每个topic 的每个分区的需要重置的Offset 信息
        List<Map<String, Map<Integer, Long>>> listTopicsResetOffset = new ArrayList<Map<String, Map<Integer, Long>>>();
        //按照Topic 拆分
        List listTopicResetOffset = Arrays.asList(resetOffsetList.split(";"));
        //循环每一个topic
        for (Object list : listTopicResetOffset) {
            //定义一个map 用来保存当前topic 的分区 offset 信息,key 是topic ，value 是key位分区号，value为offset 的子Map
            Map<String, Map<Integer, Long>> topicResetOffsetMap = new HashMap<>();
            //下面两步用来获取topic 名称
            List listTopicPartionOffset = Arrays.asList(String.valueOf(list).split("\\|"));
            String topic = String.valueOf(listTopicPartionOffset.get(0));
            //定义一个map 用来保存当前topic 每个分区的offset信息，key 是分区号，value 是具体需要重置的offset
            Map<Integer, Long> partionOffsetMap = new HashMap<Integer, Long>();
            /*for (String kv : String.valueOf(topicPartionOffset.get(1)).split(",")) {
                map.put(Integer.valueOf(kv.split(":")[0]), Long.valueOf(kv.split(":")[1]));
            }*/
            //下面两步操作是把当前Topic的每个分区的offset信息保存在 PartionOffsetMap 中
            List<String> listPartionOffset = Arrays.asList(String.valueOf(listTopicPartionOffset.get(1)).split(","));
            listPartionOffset.stream().collect(Collectors.toList()).forEach(kv -> {
                partionOffsetMap.put(Integer.valueOf(kv.split(":")[0]), Long.valueOf(kv.split(":")[1]));
            });
            System.out.println(partionOffsetMap);
            topicResetOffsetMap.put(topic, partionOffsetMap);
            listTopicsResetOffset.add(topicResetOffsetMap);
            //System.out.println(topicResetOffsetMap);
        }
        //根据从配置文件中获取的信息来做具体的offset 重置

        listTopicsResetOffset.stream().forEach(topicResetOffsetMap -> {
                    String topic = String.valueOf(topicResetOffsetMap.keySet().toArray()[0]);
                    //System.out.println(topic);
                    //System.out.println(topicResetOffsetMap.get(topic).size());
                    IntStream.range(0, (topicResetOffsetMap.get(topic)).size()).boxed().collect(Collectors.toList()).forEach(partitionUm -> {
                                System.out.println(topicResetOffsetMap.get(topic).get(partitionUm));
                                consumer.commitSync(Collections.singletonMap(new TopicPartition(topic, partitionUm), new OffsetAndMetadata(topicResetOffsetMap.get(topic).get(partitionUm))));
                            }
                    );
                }
        );
        consumer.close();
        context.close();
    }
}


