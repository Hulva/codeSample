package com.newegg.ec2.monitor.ec2kafkaoffsetmonitor.model;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年6月15日
 * @description
 *
 */
public class BrokerInfo {
	private int bid;
	private String host;
	private int port;

	public BrokerInfo(int bid, String host, int port) {
		super();
		this.bid = bid;
		this.host = host;
		this.port = port;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "BrokerInfo [bid=" + bid + ", host=" + host + ", port=" + port + "]";
	}

}
