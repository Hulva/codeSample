package com.newegg.ec2.monitor.ec2kafkaoffsetmonitor.core;

import java.util.List;

import com.newegg.ec2.monitor.ec2kafkaoffsetmonitor.model.BrokerInfo;
import com.newegg.ec2.monitor.ec2kafkaoffsetmonitor.model.OffsetInfo;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年6月15日
 * @description
 *
 */
public class KafkaInfo {

	private String name;
	private List<BrokerInfo> brokers;
	private List<OffsetInfo> offs;

	public KafkaInfo() {
		super();
	}

	public KafkaInfo(String name, List<BrokerInfo> brokers, List<OffsetInfo> offs) {
		super();
		this.name = name;
		this.brokers = brokers;
		this.offs = offs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BrokerInfo> getBrokers() {
		return brokers;
	}

	public void setBrokers(List<BrokerInfo> brokers) {
		this.brokers = brokers;
	}

	public List<OffsetInfo> getOffs() {
		return offs;
	}

	public void setOffs(List<OffsetInfo> offs) {
		this.offs = offs;
	}

	@Override
	public String toString() {
		return "KafkaInfo [name=" + name + ", brokers=" + brokers + ", offs=" + offs + "]";
	}

}
