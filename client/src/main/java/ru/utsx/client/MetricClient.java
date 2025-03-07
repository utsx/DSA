package ru.utsx.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import ru.utsx.model.CarMetric;

@RequiredArgsConstructor
public class MetricClient {

    @Value("${app.server.address}")
    private String serverAddress;

    @Value("${car.imei}")
    private String imei;

    private final RestTemplate restTemplate;

    public void sendMetric(CarMetric metric) {
        metric.setImei(imei);
        restTemplate.postForEntity("http://" +
                        serverAddress + "/metric",
                metric,
                Void.class);
    }

}
