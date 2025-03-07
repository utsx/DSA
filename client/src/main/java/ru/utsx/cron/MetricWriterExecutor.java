package ru.utsx.cron;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.utsx.client.MetricClient;
import ru.utsx.generator.CarMetricGenerator;

@Component
@RequiredArgsConstructor
public class MetricWriterExecutor {

    private final MetricClient client;
    private final CarMetricGenerator generator;

    @Scheduled(cron = "* * * * * *")
    public void writeMetric(){
        var metric = generator.generate();
        client.sendMetric(metric);
    }

}
