package ru.utsx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("tirePressure")
public class TirePressure {

    @JsonProperty("frontLeft")
    private double frontLeft;

    @JsonProperty("frontRight")
    private double frontRight;

    @JsonProperty("rearLeft")
    private double rearLeft;

    @JsonProperty("rearRight")
    private double rearRight;
}