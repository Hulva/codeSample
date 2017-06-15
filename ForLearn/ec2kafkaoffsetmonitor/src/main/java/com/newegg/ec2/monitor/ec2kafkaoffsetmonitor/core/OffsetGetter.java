package com.newegg.ec2.monitor.ec2kafkaoffsetmonitor.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.kafka.common.requests.CreateTopicsRequest.TopicDetails;
import org.apache.zookeeper.data.Stat;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newegg.ec2.monitor.ec2kafkaoffsetmonitor.model.BrokerInfo;
import com.newegg.ec2.monitor.ec2kafkaoffsetmonitor.model.Node;
import com.newegg.ec2.monitor.ec2kafkaoffsetmonitor.model.OffsetInfo;
import com.newegg.ec2.monitor.ec2kafkaoffsetmonitor.utils.ZKUtils;

import kafka.cluster.Broker;
import kafka.consumer.SimpleConsumer;
import kafka.utils.ZkUtils;
import scala.Option;
import scala.Tuple2;
import scala.collection.JavaConversions;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年6月15日
 * @description
 *
 */
public abstract class OffsetGetter {

	private static Logger LOG = LoggerFactory.getLogger(OffsetGetter.class);

	private Map<Integer, SimpleConsumer> consumerMap = new HashMap<Integer, SimpleConsumer>();

	/**
	 * <code>
	 * 			{
				    "listener_security_protocol_map": {
				        "PLAINTEXT": "PLAINTEXT"
				    },
				    "endpoints": [
				        "PLAINTEXT://10.16.238.94:8092"
				    ],
				    "jmx_port": 8888,
				    "host": "10.16.238.94",
				    "timestamp": "1497505727915",
				    "port": 8092,
				    "version": 4
				}
	 * </code>
	 * 
	 * @param bId
	 * @return
	 */
	SimpleConsumer getConsumer(int bId) {
		Tuple2<Option<String>, Stat> brokerInfo_stat = ZKUtils.getZKUtilsFromKafka()
				.readDataMaybeNull(ZkUtils.BrokerIdsPath() + "/" + bId);
		String jsonString = brokerInfo_stat._1.get();
		JSONObject brokerInfoJson = new JSONObject(jsonString);
		return new SimpleConsumer(brokerInfoJson.getString("host"), brokerInfoJson.getInt("port"), 10000, 100000,
				"ConsumerOffsetChecker");
	}

	List<OffsetInfo> processTopic(String group, String topic) {
		List<String> partitionIds = JavaConversions
				.seqAsJavaList(ZKUtils.getZKUtilsFromKafka().getChildren(ZkUtils.BrokerTopicsPath() + "/" + topic));
		List<OffsetInfo> offsetInfos = new ArrayList<OffsetInfo>();
		for (String partitionId : partitionIds) {
			offsetInfos.add(processPartition(group, topic, partitionId));
		}

		return offsetInfos;
	}

	List<BrokerInfo> brokerInfo() {
		List<BrokerInfo> binfos = new ArrayList<BrokerInfo>();
		Set<Integer> bids = consumerMap.keySet();
		BrokerInfo binfo = null;
		SimpleConsumer consumer = null;
		for (int bid : bids) {
			consumer = consumerMap.get(bid);
			binfo = new BrokerInfo(bid, consumer.host(), consumer.port());
			binfos.add(binfo);
		}
		return binfos;

	}

	List<OffsetInfo> offsetInfo(String group, List<String> topics) {
		List<OffsetInfo> offsetInfos = new ArrayList<OffsetInfo>();
		if (topics.isEmpty()) {
			topics = getTopicList(group);
		}
		for (String topic : topics) {
			offsetInfos.addAll(processTopic(group, topic));
		}
		return offsetInfos;
	}

	// get information about a consumer group and the topics it consumes
	KafkaInfo getInfo(String group, List<String> topics) {
		return new KafkaInfo(group, brokerInfo(), offsetInfo(group, topics));
	}

	List<String> getTopics() {
		List<String> topics = null;
		try {
			topics = JavaConversions
					.seqAsJavaList(ZKUtils.getZKUtilsFromKafka().getChildren(ZkUtils.BrokerTopicsPath()));
		} catch (Exception e) {
			LOG.error("could not get topics because of " + e.getMessage(), e);
		}
		return topics;
	}

	public Node getClusterViz() {
		Node rootNode = new Node("KafkaCluster");
		List<Node> childNodes = new ArrayList<Node>();
		List<Broker> brokers = JavaConversions.seqAsJavaList(ZKUtils.getZKUtilsFromKafka().getAllBrokersInCluster());
		for (Broker b : brokers) {
			// TODO b.toString()?
			childNodes.add(new Node(b.toString()));
		}
		return rootNode;
	}

	/**
	 * Returns details for a given topic such as the consumers pulling off of it
	 * 
	 * @param topic
	 * @return
	 */
	public TopicDetails getTopicDetail(String topic) {
		Map<String, List<String>> topicMap = getActiveTopicMap();

		if (topicMap.containsKey(topic)) {

		}
		return null;

	}

	abstract Map<String, List<String>> getActiveTopicMap();

	abstract List<String> getTopicList(String group);

	public abstract OffsetInfo processPartition(String group, String topic, String partitionId);

}
