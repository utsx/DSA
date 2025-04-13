package ru.utsx.streams;

import lombok.RequiredArgsConstructor;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.springframework.stereotype.Component;
import ru.utsx.functions.CarMetricFilterFunction;
import ru.utsx.model.CarMetric;

@Component
@RequiredArgsConstructor
public class FilterCarMetricStream {

    private final DataStream<CarMetric> carMetricDataStream;
    private final KafkaSink<CarMetric> kafkaSink;

    public void process() {
        carMetricDataStream.keyBy(CarMetric::getImei)
                .window(SlidingProcessingTimeWindows.of(Time.seconds(5), Time.seconds(1)))
                .process(new CarMetricFilterFunction())
                .sinkTo(kafkaSink);
    }
}
