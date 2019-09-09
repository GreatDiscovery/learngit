package com.example.mykafka;

import com.example.mykafka.core.DefaultKafkaProducerFactory;
import com.example.mykafka.core.KafkaTemplate;
import com.example.mykafka.entity.MetricEntity;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MykafkaApplicationTests {
    private Map<String, Object> configs = new HashMap<>();

    @Test
    public void contextLoads() throws InterruptedException {
        configs.put("bootstrap.servers", "10.110.25.107:9092,10.10.6.6:9092,10.110.25.105:9092");
        configs.put("client.id", "monitor-metric");
        StringSerializer serializer = new StringSerializer();
        DefaultKafkaProducerFactory defaultKafkaProducerFactory = new DefaultKafkaProducerFactory(configs, serializer, serializer);
        KafkaTemplate kafkaTemplate = new KafkaTemplate(defaultKafkaProducerFactory);
//        while (true) {
            Thread.sleep(5000);
            MetricEntity metricEntity = new MetricEntity();
            metricEntity.setMetricName("cpu");
            metricEntity.setMetricValue(0.9f);
            metricEntity.setAccount("serviceops");
            metricEntity.setResourceId("d2756b38-0b7e-4b3d-89b2-9987aaafa915");
            metricEntity.setResourceName("monitor");
            metricEntity.setTimestamp(System.currentTimeMillis());
            metricEntity.setRegion("cn-north-3");
            kafkaTemplate.send("monitor-metrics", "hello gavin!");
            kafkaTemplate.flush();
//        }
    }

}
