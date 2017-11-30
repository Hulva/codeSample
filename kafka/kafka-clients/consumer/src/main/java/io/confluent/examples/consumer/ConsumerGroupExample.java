/**
 * Copyright 2015 Confluent Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.confluent.examples.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class ConsumerGroupExample {
	private final ConsumerConnector consumer;
	private final String topic;
	private ExecutorService executor;
	private String zookeeper;
	private String groupId;
	private String url;

	public ConsumerGroupExample(String zookeeper, String groupId, String topic, String url) {
		consumer = kafka.consumer.Consumer
				.createJavaConsumerConnector(new ConsumerConfig(createConsumerConfig(zookeeper, groupId, url)));
		this.topic = topic;
		this.zookeeper = zookeeper;
		this.groupId = groupId;
		this.url = url;
	}

	private Properties createConsumerConfig(String zookeeper, String groupId, String url) {
		Properties props = new Properties();
		props.put("zookeeper.connect", zookeeper);
		props.put("group.id", groupId);
		props.put("auto.commit.enable", "true");
		props.put("auto.offset.reset", "smallest");
		props.put("offsets.storage", "zookeeper");
		// props.put("schema.registry.url", url);

		return props;
	}

	public void run(int numThreads) {
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, numThreads);

		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);

		// Launch all the threads
		executor = Executors.newFixedThreadPool(streams.size());

		// Create ConsumerLogic objects and bind them to threads
		int threadNumber = 0;
		for (final KafkaStream<byte[], byte[]> stream : streams) {
			executor.submit(new ConsumerLogic(stream, threadNumber));
			threadNumber++;
		}
	}

	public void shutdown() {
		if (consumer != null)
			consumer.shutdown();
		if (executor != null)
			executor.shutdown();
		try {
			if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
				System.out.println("Timed out waiting for consumer threads to shut down, exiting uncleanly");
			}
		} catch (InterruptedException e) {
			System.out.println("Interrupted during shutdown, exiting uncleanly");
		}
	}

	public static void main(String[] args) {
		/*if (args.length != 5) {
			System.out.println(
					"Please provide command line arguments: " + "zookeeper groupId topic threads schemaRegistryUrl");
			System.exit(-1);
		}*/

		String zooKeeper = "c7003.luva.h:2181,c7001.luva.h:2181,c7002.luva.h:2181";
		String groupId = args[0];
		String topic = args[1];
		int threads = 1;
		String url = "";

		ConsumerGroupExample example = new ConsumerGroupExample(zooKeeper, groupId, topic, url);
		example.run(threads);

//		try {
//			Thread.sleep(100000);
//		} catch (InterruptedException ie) {
//
//		}
//		example.shutdown();
	}
}
