package ru.utsx.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarMetric {

    @JsonProperty("imei")
    private String imei;

    @JsonProperty("timestamp")
    private Instant timestamp;

    @JsonProperty("speedLimit")
    private int speedLimit;

    @JsonProperty("speed")
    private double speed;

    @JsonProperty("acceleration")
    private double acceleration;

    @JsonProperty("tire_pressure")
    private TirePressure tirePressure;

    @JsonProperty("axis_deviation")
    private AxisDeviation axisDeviation;

    @JsonProperty("engine_rpm")
    private int engineRpm;

    @JsonProperty("pedal_force")
    private PedalForce pedalForce;
}
