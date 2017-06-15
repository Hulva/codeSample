package com.newegg.ec2.monitor.ec2kafkaoffsetmonitor.model;

import java.util.List;

/**
 * @author Hulva Luva.H from ECBD
 * @date 2017年6月15日
 * @description
 *
 */
public class Node {
	private String name;
	private List<Node> children;

	public Node() {
		super();
	}

	public Node(String name, List<Node> children) {
		super();
		this.name = name;
		this.children = children;
	}

	public Node(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "Node [name=" + name + ", children=" + children + "]";
	}

}
