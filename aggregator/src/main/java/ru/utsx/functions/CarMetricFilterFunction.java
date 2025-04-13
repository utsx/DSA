package ru.utsx.functions;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import ru.utsx.model.CarMetric;

public class CarMetricFilterFunction extends ProcessWindowFunction<CarMetric, CarMetric, String, TimeWindow> {

    @Override
    public void process(String key, Context context, Iterable<CarMetric> elements, Collector<CarMetric> out) throws Exception {
        CarMetric lastValidCarMetric = null;

        for (CarMetric carMetric : elements) {
            if (isValid(carMetric)) {
                out.collect(carMetric);
                lastValidCarMetric = carMetric;
            }
        }
        if (lastValidCarMetric != null) {
            out.collect(lastValidCarMetric);
        }
    }

    private boolean isValid(CarMetric carMetric) {
        return carMetric.getEngineRpm() >= 0 && carMetric.getSpeed() >= 0;
    }
}
