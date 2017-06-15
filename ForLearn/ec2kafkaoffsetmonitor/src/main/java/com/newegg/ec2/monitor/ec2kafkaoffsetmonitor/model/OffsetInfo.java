package com.newegg.ec2.monitor.ec2kafkaoffsetmonitor.model;

import org.apache.kafka.common.utils.Time;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年6月15日
 * @description
 *
 */
public class OffsetInfo {
	private String group;
	private String topic;
	private int partition;
	private Long offset;
	private Long logSize;
	private String owner;
	private Time creation;
	private Time modified;
	private Long lag;

	public OffsetInfo() {
		super();
	}

	public OffsetInfo(String group, String topic, int partition, Long offset, Long logSize, String owner, Time creation,
			Time modified) {
		super();
		this.group = group;
		this.topic = topic;
		this.partition = partition;
		this.offset = offset;
		this.logSize = logSize;
		this.owner = owner;
		this.creation = creation;
		this.modified = modified;
		setLag();
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public int getPartition() {
		return partition;
	}

	public void setPartition(int partition) {
		this.partition = partition;
	}

	public Long getOffset() {
		return offset;
	}

	public void setOffset(Long offset) {
		this.offset = offset;
	}

	public Long getLogSize() {
		return logSize;
	}

	public void setLogSize(Long logSize) {
		this.logSize = logSize;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Time getCreation() {
		return creation;
	}

	public void setCreation(Time creation) {
		this.creation = creation;
	}

	public Time getModified() {
		return modified;
	}

	public void setModified(Time modified) {
		this.modified = modified;
	}

	public Long getLag() {
		return lag;
	}

	public void setLag() {
		this.lag = logSize - offset;
	}

}
