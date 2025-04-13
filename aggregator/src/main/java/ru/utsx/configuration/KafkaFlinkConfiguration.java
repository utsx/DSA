package ru.utsx.configuration;

import java.util.Objects;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.formats.json.JsonDeserializationSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.springframework.context.annotation.Bean;
import ru.utsx.model.CarMetric;
import ru.utsx.model.RpmWarning;

public class KafkaFlinkConfiguration {

    private static final String bootstrapServers = "kafka:29092,kafka2:29093";
    private static final String groupId = "analyzer";
    private static final String inputTopic = "car_metric";

    @Bean
    public StreamExecutionEnvironment streamExecutionEnvironment() {
        return StreamExecutionEnvironment.getExecutionEnvironment();
    }

    @Bean
    public KafkaSource<CarMetric> kafkaSource() {
        return KafkaSource.<CarMetric>builder()
                .setBootstrapServers(bootstrapServers)
                .setTopics(inputTopic)
                .setGroupId(groupId)
                .setStartingOffsets(OffsetsInitializer.committedOffsets(OffsetResetStrategy.LATEST))
                .setValueOnlyDeserializer(new JsonDeserializationSchema<>(CarMetric.class))
                .build();
    }

    @Bean
    public KafkaSink<RpmWarning> rpmWarningKafkaSink(
            KafkaRecordSerializationSchema<RpmWarning> rpmWarningKafkaSerializationSchema) {
        return KafkaSink.<RpmWarning>builder()
                .setBootstrapServers(bootstrapServers)
                .setRecordSerializer(rpmWarningKafkaSerializationSchema)
                .build();
    }

    @Bean
    public KafkaSink<CarMetric> filteredCarMetricKafkaSink(
            KafkaRecordSerializationSchema<CarMetric> carMetricKafkaSerializationSchema) {
        return KafkaSink.<CarMetric>builder()
                .setBootstrapServers(bootstrapServers)
                .setRecordSerializer(carMetricKafkaSerializationSchema)
                .build();
    }

    @Bean
    public DataStream<CarMetric> carMetricDataStream(StreamExecutionEnvironment env, KafkaSource<CarMetric> kafkaSource) {
        return env.fromSource(kafkaSource, WatermarkStrategy.noWatermarks(), "Kafka Source")
                .filter(Objects::nonNull);
    }


}
