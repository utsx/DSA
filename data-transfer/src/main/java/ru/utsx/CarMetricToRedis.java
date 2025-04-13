package ru.utsx;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import ru.utsx.model.CarMetric;

@Component
@RequiredArgsConstructor
public class CarMetricToRedis {

    private final KafkaConsumer<String, CarMetric> carMetricKafkaConsumer;
    private final RedisTemplate<String, CarMetric> redisCarMetricTemplate;

    @PostConstruct
    public void init() {
        new Thread(this::process).start();
    }

    public void process() {
        while (true) {
            ConsumerRecords<String, CarMetric> records = carMetricKafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, CarMetric> record : records) {
                CarMetric carMetric = record.value();
                writeToRedis(carMetric);
            }
        }
    }

    private void writeToRedis(CarMetric carMetric) {
        redisCarMetricTemplate.opsForValue().set(carMetric.getImei(), carMetric);
    }
}
