package ru.utsx.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName("rpmWarning")
public class RpmWarning {

    @JsonProperty("imei")
    private String imei;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("warning")
    private boolean warning;

}
