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
@JsonRootName("pedalForce")
public class PedalForce {

    @JsonProperty("accelerator")
    private double accelerator;

    @JsonProperty("brake")
    private double brake;
}
