package com.thisara.consumer;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.thisara.connectors.JavaMongoConnect;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.bson.Document;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Properties;

@RestController
@RequestMapping("/consume")
public class ConsumerService {

	private Logger logger = LoggerFactory.getLogger(ConsumerService.class);

	private final String kafkaServer = "localhost:9092";
	private final static String TOPIC_NAME = "myfleet_DADAT001_error_log";
	private final static String MONGO_HOST = "localhost";
	private final static int MONGO_PORT = 27017;
	private final static String MONGO_DATABASE = "logs";
	private final static String MONGO_COLLECTION = "errorlogs";
	@Autowired
	Environment env;

	@Autowired
	private RebalanceListener rebalanceListener;

	@GetMapping("/message/")
	public String getMessage() throws Exception {

		//runConsumer();

		// Set up MongoDB client and database
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);

		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");

		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(Collections.singletonList(TOPIC_NAME));

		ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
		if (records.isEmpty()) {
			return "No new records found in Kafka topic.";
		}

		MongoClient mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
		MongoDatabase database = mongoClient.getDatabase(MONGO_DATABASE);
		MongoCollection<Document> collection = database.getCollection(MONGO_COLLECTION);

		records.forEach(record -> {
			Document document = new Document("key", record.key())
					.append("value", record.value())
					.append("timestamp", record.timestamp());
			collection.insertOne(document);
		});

		consumer.commitSync();
		consumer.close();
		mongoClient.close();

		return "Successfully consumed latest records from Kafka topic and saved to MongoDB.";



}

	private KafkaConsumer<String, String> createConsumer() {

		Properties properties = new Properties();

		properties.put("group.id", env.getProperty("consumer.group.id"));
		properties.put("bootstrap.servers", env.getProperty("consumer.bootstrap.servers"));
		properties.put("key.deserializer", env.getProperty("consumer.key.deserializer"));
		properties.put("value.deserializer", env.getProperty("consumer.value.deserializer"));

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

		consumer.subscribe(Collections.singletonList(env.getProperty("consumer.topic.name")));

		return consumer;
	}

	private void runConsumer() throws InterruptedException {

		KafkaConsumer<String, String> consumer = createConsumer();

		// TODO - Constructor Injection
		rebalanceListener.setKafkaConsumer(consumer);

		final int allowedEmptyAttempts = Integer.parseInt(env.getProperty("consumer.poll.attempts.empty.max"));
		int emptyAttempts = 0;

		LocalDateTime dateTimeNow = LocalDateTime.now();

		try {

			while (true) {

				final ConsumerRecords<String, String> consumerRecords = consumer
						.poll(Duration.ofMillis(Long.parseLong(env.getProperty("consumer.poll.interval"))));

				try {

					if (consumerRecords.count() == 0) {
						emptyAttempts++;
						if (emptyAttempts > allowedEmptyAttempts)
							break;
						else
							continue;
					}

					consumerRecords.forEach(record -> {

						String topic = record.topic();
						int partition = record.partition();
						long offset = record.offset();

						JSONObject topicInfo = getTopicInfo(topic, partition, offset);

						boolean publishStatus = JavaMongoConnect.getJavaMongoConnect().saveMessage(record.key(),
								record.value(), dateTimeNow, topic, topicInfo);

						logger.info(record.key() + " Publish status : " + publishStatus);

						rebalanceListener.addOffset(topic, partition, offset);
					});

				} catch (Exception e) {
					logger.error("Error reading message : " + e.getLocalizedMessage());
				}
			}

		} catch (Exception e) {
			logger.error("Error reading messages! " + e.getLocalizedMessage());
		} finally {
			consumer.commitSync();
			consumer.close();
			logger.info("Consumer closed!");
		}
	}

	public JSONObject getTopicInfo(String topicName, int partitionNumber, long offset) {

		JSONObject topicInfo = new JSONObject();

		topicInfo.put("topic", topicName);
		topicInfo.put("partition", partitionNumber);
		topicInfo.put("offset", offset);

		return topicInfo;
	}
}
