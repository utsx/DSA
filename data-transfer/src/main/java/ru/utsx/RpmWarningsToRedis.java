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
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import ru.utsx.model.RpmWarning;

@Component
@RequiredArgsConstructor
public class RpmWarningsToRedis {

    private final KafkaConsumer<String, RpmWarning> rpmWarningKafkaConsumer;
    private final RedisTemplate<String, RpmWarning> redisRpmWarningTemplate;

    @PostConstruct
    public void init() {
        new Thread(this::process).start();
    }

    public void process() {
        int batchSize = 30;
        List<RpmWarning> messageBatch = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, RpmWarning> records = rpmWarningKafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, RpmWarning> record : records) {
                messageBatch.add(record.value());
                if (messageBatch.size() >= batchSize) {
                    writeToRedis(messageBatch);
                    messageBatch.clear();
                }
            }
            if (!messageBatch.isEmpty()) {
                writeToRedis(messageBatch);
                messageBatch.clear();
            }
        }
    }

    private void writeToRedis(Collection<RpmWarning> messages) {
        for (RpmWarning message : messages) {
            String PREFIX = "WARNING_";
            redisRpmWarningTemplate.opsForList().leftPush(PREFIX + message.getImei(), message);
        }
    }
}

