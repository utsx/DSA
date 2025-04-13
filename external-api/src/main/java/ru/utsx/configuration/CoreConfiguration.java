package ru.utsx.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(
        RedisConfiguration.class
)
@Configuration
public class CoreConfiguration {
}
