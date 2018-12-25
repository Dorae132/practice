package com.nsb.practice.algorithm.graphic;

import java.util.List;

/**
 * 克鲁斯卡尔
 * @author Dorae
 *
 */
public class Kruskal {

	public static void main(String[] args) {
		kruskal(Constants.EDGES, Constants.GRAPH.length);
	}
	
	/**
	 * @param edges 边集合，有序
	 * @param n 顶点数
	 */
	static void kruskal(List<Edge> edges, int n) {
		int[] vset = new int[n]; // 并查集
		// 初始化并查集
		for (int i = 0; i < n; i++) {
			vset[i] = i;
		}
		int k = 1;// 当前第几条边
		int j = 0;// edges下标
		while (k < n) {
			Edge edge = edges.get(j);
			if (vset[edge.getV1()] != vset[edge.getV2()]) {
				// 选中边
				System.out.println(edge.getV1() + ", " + edge.getV2() + " 权重：" + edge.getWeight());
				k++;
				// 合并
				for (int i = 0; i < n; i++) {
					if (vset[i] == vset[edge.getV2()]) {
						vset[i] = vset[edge.getV1()];
					}
				}
			}
			j++;	
		}
	}
}
