package com.nsb.practice.algorithm.graphic;

/**
 * 头结点
 * 
 * @author Dorae
 *
 */
public class HeadNode extends Node {

	private TableNode firstArc;// 第一条弧

	private int inArcCount;// 入度

	private int outArcCount;// 出度

	public HeadNode(Integer data, TableNode firstArc, int inArcCount) {
		super();
		this.data = data;
		this.firstArc = firstArc;
		this.inArcCount = inArcCount;
	}

	/**
	 * @return the inArcCount
	 */
	public int getInArcCount() {
		return inArcCount;
	}

	/**
	 * @param inArcCount the inArcCount to set
	 */
	public void setInArcCount(int inArcCount) {
		this.inArcCount = inArcCount;
	}

	public TableNode getFirstArc() {
		return firstArc;
	}

	public void setFirstArc(TableNode firstArc) {
		this.firstArc = firstArc;
	}
}
