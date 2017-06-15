package com.newegg.ec2.monitor.ec2kafkaoffsetmonitor.utils;

import java.util.List;
import java.util.Set;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.cluster.Broker;
import kafka.common.TopicAndPartition;
import kafka.utils.ZkUtils;
import scala.Option;
import scala.collection.JavaConversions;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年6月15日
 * @description
 *
 */
public class ZKUtils {
	private Logger LOG = LoggerFactory.getLogger(ZKUtils.class);

	private final String consumersPath = ZkUtils.ConsumersPath();

	private static ZkClient zkClient = null;
	private static ZkConnection zkConnection = null;
	private static ZkUtils zkUtilsFromKafka = null;

	public void init(String zkHosts) {
		if (zkClient == null) {
			zkClient = new ZkClient(zkHosts);
		}
		if (zkConnection == null) {
			zkConnection = new ZkConnection(zkHosts);
		}
		if (zkUtilsFromKafka == null) {
			zkUtilsFromKafka = new ZkUtils(zkClient, zkConnection, false);
		}
	}

	public static ZkUtils getZKUtilsFromKafka() {
		return zkUtilsFromKafka;
	}

	public static Set<TopicAndPartition> getAllPartitions() {
		return JavaConversions.setAsJavaSet(zkUtilsFromKafka.getAllPartitions());
	}

	public static List<String> getAllTopics() {
		return JavaConversions.seqAsJavaList(zkUtilsFromKafka.getAllTopics());
	}

	public static Option<Broker> getBrokerInfo(int brokerId) {
		// Option[Broker] = zkUtilsFromKafka.getBrokerInfo(zkClient, brokerId)
		return zkUtilsFromKafka.getBrokerInfo(brokerId);
	}

	public static List<String> getConsumersInGroup(String group) {
		return JavaConversions.seqAsJavaList(zkUtilsFromKafka.getConsumersInGroup(group));

	}

	public static List<String> parseTopicsData(String jsonData) {
		return JavaConversions.seqAsJavaList(ZkUtils.parseTopicsData(jsonData));
	}

	public static boolean pathExists(String path) {
		return zkUtilsFromKafka.pathExists(path);
	}

	public static List<String> getChildrenParentMayNotExist(String path) {
		return JavaConversions.seqAsJavaList(zkUtilsFromKafka.getChildrenParentMayNotExist(path));
	}

	public static List<String> getChildren(String path) {
		return JavaConversions.seqAsJavaList(zkUtilsFromKafka.getChildren(path));
	}

}
