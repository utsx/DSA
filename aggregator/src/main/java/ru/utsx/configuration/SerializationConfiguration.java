package ru.utsx.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.formats.json.JsonSerializationSchema;
import org.springframework.context.annotation.Bean;
import ru.utsx.model.CarMetric;
import ru.utsx.model.RpmWarning;

public class SerializationConfiguration {

    private static final String outputTopic = "rpm_warning";
    private static final String carMetricOutPutTopic = "filtered_car_metric";

    @Bean
    public KafkaRecordSerializationSchema<RpmWarning> rpmWarningKafkaSerializationSchema() {
        return KafkaRecordSerializationSchema.builder()
                .setValueSerializationSchema(new JsonSerializationSchema<RpmWarning>())
                .setTopic(outputTopic)
                .build();
    }

    @Bean
    public KafkaRecordSerializationSchema<CarMetric> carMetricKafkaSerializationSchema() {
        return KafkaRecordSerializationSchema.builder()
                .setValueSerializationSchema(new JsonSerializationSchema<CarMetric>())
                .setTopic(carMetricOutPutTopic)
                .build();
    }

}
