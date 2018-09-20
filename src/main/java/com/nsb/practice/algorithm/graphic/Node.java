package com.nsb.practice.algorithm.graphic;

/**
 * 顶层节点类
 * @author Dorae
 *
 */
public abstract class Node {

	protected Integer data;// 数据域
	
	protected Integer arcCount;// 度

	/**
	 * @return the data
	 */
	public Integer getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Integer data) {
		this.data = data;
	}

	/**
	 * @return the arcCount
	 */
	public Integer getArcCount() {
		return arcCount;
	}

	/**
	 * @param arcCount the arcCount to set
	 */
	public void setArcCount(Integer arcCount) {
		this.arcCount = arcCount;
	}
}
