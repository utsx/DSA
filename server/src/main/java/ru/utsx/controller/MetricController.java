package ru.utsx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.utsx.model.CarMetric;

@Controller
@RequiredArgsConstructor
public class MetricController {

    private final KafkaTemplate<String, CarMetric> carMetricKafkaTemplate;

    @PostMapping("/metric")
    public ResponseEntity<Void> metric(@RequestBody CarMetric metric){
        carMetricKafkaTemplate.send("car_metric", metric);
        return ResponseEntity.ok().build();
    }

}
