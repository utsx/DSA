package ru.utsx.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.utsx.model.RpmWarning;

@RestController
@RequestMapping("/warning")
@RequiredArgsConstructor
public class WarningController {

    private final RedisTemplate<String, RpmWarning> redisRpmWarningTemplate;

    @GetMapping("/rpm/{imei}")
    public List<RpmWarning> getWarning(@PathVariable("imei") String imei) {
        String PREFIX = "WARNING_";
        return redisRpmWarningTemplate.opsForList().range(PREFIX + imei, 0, -1);
    }

}
