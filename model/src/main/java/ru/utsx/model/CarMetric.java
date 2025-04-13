package ru.utsx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("carMetric")
public class CarMetric {

    @JsonProperty("imei")
    private String imei;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("speedLimit")
    private int speedLimit;

    @JsonProperty("speed")
    private double speed;

    @JsonProperty("acceleration")
    private double acceleration;

    @JsonProperty("tirePressure")
    private TirePressure tirePressure;

    @JsonProperty("axisDeviation")
    private AxisDeviation axisDeviation;

    @JsonProperty("engineRpm")
    private int engineRpm;

    @JsonProperty("pedalForce")
    private PedalForce pedalForce;
}
