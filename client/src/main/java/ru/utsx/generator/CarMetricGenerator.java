package ru.utsx.generator;

import com.github.javafaker.Faker;

import java.time.Instant;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.utsx.model.AxisDeviation;
import ru.utsx.model.CarMetric;
import ru.utsx.model.PedalForce;
import ru.utsx.model.TirePressure;

@Component
@RequiredArgsConstructor
public class CarMetricGenerator {

    private final Faker faker;
    private final Random random;

    public CarMetric generate() {
        ru.utsx.model.TirePressure tirePressure = TirePressure.builder()
                .frontLeft(faker.number().randomDouble(1, 30, 35))
                .frontRight(faker.number().randomDouble(1, 30, 35))
                .rearLeft(faker.number().randomDouble(1, 30, 35))
                .rearRight(faker.number().randomDouble(1, 30, 35))
                .build();

        AxisDeviation axisDeviation = AxisDeviation.builder()
                .xAxis(faker.number().randomDouble(2, -1, 1))
                .yAxis(faker.number().randomDouble(2, -1, 1))
                .zAxis(faker.number().randomDouble(2, -1, 1))
                .build();

        PedalForce pedalForce = PedalForce.builder()
                .accelerator(faker.number().randomDouble(1, 0, 100))
                .brake(faker.number().randomDouble(1, 0, 100))
                .build();

        return CarMetric.builder()
                .timestamp(Instant.now().toString())
                .speedLimit(faker.number().numberBetween(50, 120))
                .speed(faker.number().randomDouble(2, 0, 120))
                .acceleration(faker.number().randomDouble(2, -5, 5))
                .tirePressure(tirePressure)
                .axisDeviation(axisDeviation)
                .engineRpm(faker.number().numberBetween(900, 7000))
                .pedalForce(pedalForce)
                .build();
    }
}
