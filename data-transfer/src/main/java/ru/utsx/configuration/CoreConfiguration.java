package ru.utsx.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        RedisConfiguration.class,
        KafkaConsumerConfiguration.class
})
@Configuration
public class CoreConfiguration {
}
