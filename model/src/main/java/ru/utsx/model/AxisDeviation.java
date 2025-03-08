package ru.utsx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AxisDeviation {

    @JsonProperty("xAxis")
    private double xAxis;

    @JsonProperty("yAxis")
    private double yAxis;

    @JsonProperty("zAxis")
    private double zAxis;
}