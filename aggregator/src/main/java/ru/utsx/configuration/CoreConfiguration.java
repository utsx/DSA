package ru.utsx.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(
        {
                KafkaFlinkConfiguration.class,
                SerializationConfiguration.class
        }
)
@Configuration
public class CoreConfiguration {
}
