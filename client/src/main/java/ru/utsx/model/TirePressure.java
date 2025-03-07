package ru.utsx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TirePressure {

    @JsonProperty("front_left")
    private double frontLeft;

    @JsonProperty("front_right")
    private double frontRight;

    @JsonProperty("rear_left")
    private double rearLeft;

    @JsonProperty("rear_right")
    private double rearRight;
}