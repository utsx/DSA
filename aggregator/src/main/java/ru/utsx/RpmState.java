package ru.utsx;

import lombok.Getter;

@Getter
public class RpmState {
    private double lastRpm;
    private long lastTimestamp;

    public RpmState(double lastRpm, long lastTimestamp) {
        this.lastRpm = lastRpm;
        this.lastTimestamp = lastTimestamp;
    }

    public void setLastRpm(double lastRpm, long lastTimestamp) {
        this.lastRpm = lastRpm;
        this.lastTimestamp = lastTimestamp;
    }
}
