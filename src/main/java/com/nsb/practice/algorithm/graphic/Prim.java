package com.nsb.practice.algorithm.graphic;

/**
 * 普利姆
 * @author Dorae
 *
 */
public class Prim {
	static int MAX = Constants.MAX;
 
	public static void main(String[] args) {
		prim(Constants.GRAPH, Constants.GRAPH.length, 0);
	}
	 
	 /**
	  * @param graph 邻接矩阵
	  * @param n 定点数
	  * @param v 起始顶点
	  */
	 public static void prim(int[][] graph, int n, int v) {
	     // 两个集合间的花费
		 int[] lowcost = new int [n];
		 int min = Integer.MAX_VALUE;
		 // 两个集合间最花费最小值的中间节点，closest中为已经加入集合的顶点
		 int[] closest = new int[n];
		 int i, j, k = 0;
		 // 初始化辅助数组
		 for (i = 0; i < n; i++) {
			 lowcost[i] = graph[v][i];
			 closest[i] = v;
		 }
		 // 找出n-1个顶点
		 for (i = 1; i < n; i++) {
			 min = Integer.MAX_VALUE;
			 // 找出距离当前集合最近的顶点
			 for (j = 0; j<n; j++) {
				 if (lowcost[j] != 0 && lowcost[j] < min) {
					 min = lowcost[j];
					 k = j;
				 }
			 }
			 System.out.println(closest[k] + "->" + k + "权重为：" + min);
			 // 标记节点已经加入目标集合
			 lowcost[k] = 0;
			 // 更新其他节点到集合的距离
			 for (j = 0; j<n; j++) {
				 if (lowcost[j] != 0 && lowcost[j] > graph[k][j]) {
					lowcost[j] = graph[k][j];
					// 更新中间节点
					closest[j] = k;
				}
			 }
		 }
	 }
	    
}