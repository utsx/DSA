package ru.utsx.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ru.utsx.model.CarMetric;
import ru.utsx.model.RpmWarning;

@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, CarMetric> redisCarMetricTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, CarMetric> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, RpmWarning> redisRpmWarningTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, RpmWarning> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}