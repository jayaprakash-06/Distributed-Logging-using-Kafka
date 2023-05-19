package com.thisara.sedat001;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;


@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {

	@Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
	public KafkaStreamsConfiguration kStreamsConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-app");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
		return new KafkaStreamsConfiguration(props);
	}

	@Bean
	public KStream<String, String> kStream(StreamsBuilder streamsBuilder) {
		KStream<String, String> stream = streamsBuilder.stream("error-logs");

		Predicate<String, String> filterCondition1 = (key, value) -> value.contains("CODAT002");
		Predicate<String, String> filterCondition2 = (key, value) -> value.contains("DADAT001");

		KStream<String, String>[] branches = stream
				.branch(filterCondition1, filterCondition2, (key, value) -> true);

		branches[0].to("myfleet_CODAT002_error_log");
		branches[1].to("myfleet_DADAT001_error_log");

		return stream;
	}
}
