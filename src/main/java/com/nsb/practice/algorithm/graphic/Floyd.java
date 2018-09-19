package com.nsb.practice.algorithm.graphic;

/**
 * Floyd
 * @author Dorae
 *
 */
public class Floyd {

	public static void main(String[] args) {
		floyd(Constants.DIRECTED_GRAPH, Constants.DIRECTED_GRAPH.length);
	}
	
	/**
	 * 
	 * @param graph
	 * @param n 顶点数
	 */
	static void floyd(int[][] graph, int n) {
		int[][] A = new int[n][n];// 存放中间结果
		int[][] path = new int[n][n];// 存放前驱节点，追溯路径
		
		// 1 初始化
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = graph[i][j];
				path[i][j] = -1;
			}
		}
		// calculate
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (A[i][k] != Constants.MAX
							&& A[k][j] != Constants.MAX
							&& A[i][j] > (A[i][k] + A[k][j])) {
						A[i][j] = A[i][k] + A[k][j];
						path[i][j] = k;
					}
				}
			}
		}
		// out
		printPath(A, path, n);
	}

	private static void printPath(int[][] A, int[][] path, int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (A[i][j] == Constants.MAX && i != j) {
					System.out.println(i + "到" + j + "之间没有路径");
				} else {
					System.out.print(i + "到" + j + "之间路径为：" + i + "->");
					ppath(path, i, j);
					System.out.print(j + "路径长度为：" + A[i][j] + "\n");
				}
			}
		}
	}

	private static void ppath(int[][] path, int i, int j) {
		int k = path[i][j];
		if (k == -1) {
			return;
		}
		ppath(path, i, k);
		System.out.print(k + "->");
		ppath(path, k, j);
	}
}
