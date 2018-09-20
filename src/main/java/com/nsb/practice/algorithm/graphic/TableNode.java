package com.nsb.practice.algorithm.graphic;

/**
 * 表节点
 * @author Dorae
 *
 */
public class TableNode extends Node {

	private TableNode next;

	public TableNode(Integer data, TableNode next) {
		super();
		this.next = next;
		this.data = data;
	}

	/**
	 * @return the next
	 */
	public TableNode getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(TableNode next) {
		this.next = next;
	}
	
}
