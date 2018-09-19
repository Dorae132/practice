package com.nsb.practice.algorithm.graphic;

/**
 * 边
 * @author Dorae
 *
 */
public class Edge {
	
	private Integer v1;
	
	private Integer v2;
	
	private Integer weight;

	@Override
	public String toString() {
		return new StringBuilder().append(v1)
				.append(", ").append(v2)
				.append(" 权重：").append(weight).append(";").toString();
	}

	public Edge(Integer v1, Integer v2, Integer weight) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}

	public Integer getV1() {
		return v1;
	}

	public void setV1(Integer v1) {
		this.v1 = v1;
	}

	public Integer getV2() {
		return v2;
	}

	public void setV2(Integer v2) {
		this.v2 = v2;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
