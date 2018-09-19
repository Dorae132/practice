package com.nsb.practice.algorithm.graphic;

/**
 * 狄克斯特拉算法
 * @author Dorae
 *
 */
public class Dijkstra {
	
	public static void main(String[] args) {
		diskstra(Constants.DIRECTED_GRAPH, Constants.DIRECTED_GRAPH.length, 3);
	}
	
	/**
	 * diskstra
	 * @param graph
	 * @param n 顶点数
	 * @param v0 起始点
	 */
	static void diskstra(int[][] graph, int n, int v0) {
		int[] dist = new int[n];// 保存目前v0到vi的最短路径，可能会被不断修改
		int[] path = new int[n];// v0到vi最短路径的vi的前驱节点
		boolean[] s = new boolean[n];// 当前已经找到最短路径的点
		
		// 1 初始化
		for (int i = 0; i < n; i++) {
			dist[i] = graph[v0][i];
			s[i] = false;
			if (graph[v0][i] < Constants.MAX) {
				path[i] = v0;
			} else {
				path[i] = -1;
			}
		}
		// 2 源点v0放入s
		s[v0] = true;
		path[v0] = 0;
		int minDist = -1;
		int u = v0;
		for (int i = 1; i < n; i++) {
			minDist = Constants.MAX;
			// 3 选出不在s中，且具有最小距离的顶点u
			for (int j = 1; j < n; j++) {
				if (!s[j] && dist[j] < minDist) {
					u = j;
					minDist = dist[j];
				}
			}
			// 顶点u加入s
			s[u] = true;
			// 4 修改不在s中的顶点距离
			for (int j = 1; j < n; j++) {
				if (!s[j] && graph[u][j] < Constants.MAX && dist[u] + graph[u][j] < dist[j]) {
					dist[j] = dist[u] + graph[u][j];
					path[j] = u;
				}
			}
		}
		printPath(dist, path, s, n, v0);
	}

	/**
	 * 输出最短路径
	 * @param dist
	 * @param path
	 * @param s
	 * @param n
	 * @param v0
	 */
	static void printPath(int[] dist, int[] path, boolean[] s, int n, int v0) {
		int i, k;
		for (i = 0; i < n; i++) {
			if (s[i]) {
				k = i;
				System.out.print(v0 + "到" + i + "的最短路径为：");
				while (k != v0) {
					System.out.print(k + "<-");
					k = path[k];
				}
				System.out.print(v0 +" 路径长度为：" + dist[i] + "\n\n");
			} else {
				System.out.println(v0 + "到" + i + "不存在路径");
			}
		}
	}
}
