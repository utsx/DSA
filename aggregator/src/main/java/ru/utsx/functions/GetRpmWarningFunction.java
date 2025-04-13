package ru.utsx.functions;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import ru.utsx.RpmState;
import ru.utsx.model.CarMetric;
import ru.utsx.model.RpmWarning;

public class GetRpmWarningFunction extends ProcessWindowFunction<CarMetric, RpmWarning, String, TimeWindow> {

    @Override
    public void process(String key, Context context, Iterable<CarMetric> elements, Collector<RpmWarning> out) {
        for (CarMetric carMetric : elements) {
            if (carMetric.getEngineRpm() > 4000) {
                RpmWarning warning = RpmWarning.builder()
                        .imei(carMetric.getImei())
                        .timestamp(carMetric.getTimestamp())
                        .warning(true)
                        .build();
                out.collect(warning);
            }
        }
    }

}

