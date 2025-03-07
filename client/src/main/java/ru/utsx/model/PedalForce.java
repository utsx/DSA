package ru.utsx.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedalForce {

    @JsonProperty("accelerator")
    private double accelerator;

    @JsonProperty("brake")
    private double brake;
}
