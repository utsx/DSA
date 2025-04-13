package ru.utsx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.utsx.model.CarMetric;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final RedisTemplate<String, CarMetric> redisCarMetricTemplate;

    @RequestMapping("/{imei}")
    public CarMetric getLastCarMetric(@PathVariable("imei") String imei) {
        return redisCarMetricTemplate.opsForValue().get(imei);
    }

}
