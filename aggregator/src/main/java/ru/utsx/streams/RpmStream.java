package ru.utsx.streams;

import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.windowing.assigners.SlidingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.springframework.stereotype.Component;
import ru.utsx.functions.GetRpmWarningFunction;
import ru.utsx.model.CarMetric;
import ru.utsx.model.RpmWarning;

@Component
@RequiredArgsConstructor
public class RpmStream {

    private final DataStream<CarMetric> carMetricDataStream;
    private final KafkaSink<RpmWarning> kafkaSink;

    public void process() {
        carMetricDataStream.keyBy(CarMetric::getImei)
                .window(SlidingProcessingTimeWindows.of(Time.seconds(5), Time.seconds(1)))
                .process(new GetRpmWarningFunction())
                .sinkTo(kafkaSink);
    }

}
