package ru.utsx.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.utsx.client.MetricClient;

@Configuration
public class ClientsConfiguration {

    @Bean
    public MetricClient metricClient(RestTemplate metricRestTemplate) {
        return new MetricClient(metricRestTemplate);
    }

    @Bean
    public RestTemplate metricRestTemplate() {
        return new RestTemplate();
    }

}
