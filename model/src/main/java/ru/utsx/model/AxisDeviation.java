package ru.utsx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("axisDeviation")
public class AxisDeviation {

    @JsonProperty("xaxis")
    private double xAxis;

    @JsonProperty("yaxis")
    private double yAxis;

    @JsonProperty("zaxis")
    private double zAxis;
}