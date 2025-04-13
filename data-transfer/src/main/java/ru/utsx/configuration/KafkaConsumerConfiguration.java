package ru.utsx.configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import ru.utsx.model.CarMetric;
import ru.utsx.model.RpmWarning;

@EnableKafka
public class KafkaConsumerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Bean("rpmWarningKafkaConsumer")
    public KafkaConsumer<String, RpmWarning> rpmWarningKafkaConsumer() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "analyzer");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, RpmWarning.class.getName());
        KafkaConsumer<String, RpmWarning> consumer = new KafkaConsumer<>(configProps);
        String topic = "rpm_warning";
        consumer.subscribe(Collections.singleton(topic));
        return consumer;
    }

    @Bean("carMetricKafkaConsumer")
    public KafkaConsumer<String, CarMetric> carMetricKafkaConsumer() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "analyzer");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(JsonDeserializer.VALUE_DEFAULT_TYPE, CarMetric.class.getName());
        KafkaConsumer<String, CarMetric> consumer = new KafkaConsumer<>(configProps);
        String topic = "filtered_car_metric";
        consumer.subscribe(Collections.singleton(topic));
        return consumer;
    }
}
