package com.nsb.practice.algorithm.graphic;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.Lists;

public class Constants {
	
	public static int MAX = Integer.MAX_VALUE;
	
	// 带权无向图
	public static int[][] GRAPH = new int[][] {
		{ 0, 10, MAX, MAX, MAX, 11, MAX, MAX, MAX },
		{ 10, 0, 18, MAX, MAX, MAX, 16, MAX, 12 },
		{ MAX, MAX, 0, 22, MAX, MAX, MAX, MAX, 8 },
		{ MAX, MAX, 22, 0, 20, MAX, MAX, 16, 21 },
		{ MAX, MAX, MAX, 20, 0, 26, MAX, 7, MAX },
		{ 11, MAX, MAX, MAX, 26, 0, 17, MAX, MAX },
		{ MAX, 16, MAX, MAX, MAX, 17, 0, 19, MAX },
		{ MAX, MAX, MAX, 16, 7, MAX, 19, 0, MAX },
		{ MAX, 12, 8, 21, MAX, MAX, MAX, MAX, 0 } };

	// 带权无向图边集合，有序
	public static List<Edge> EDGES = Lists.newArrayList();
	
	// 带权有向图
	public static int[][] DIRECTED_GRAPH = new int[][] {
		{0, 4, 6, 6, MAX, MAX, MAX},
		{MAX, 0, 1, MAX, 7, MAX, MAX},
		{MAX, MAX, 0, MAX, 6, 4, MAX},
		{MAX, MAX, 2, 0, MAX, 5, MAX},
		{MAX, MAX, MAX, MAX, 0, MAX, 6},
		{MAX, MAX, MAX, MAX, 1, 0, 8},
		{MAX, MAX, MAX, MAX, MAX, MAX, 0}
	};
	
	static {
		for (int i = 0; i < GRAPH.length; i++) {
			for (int j = i; j < GRAPH.length; j++) {
				if (GRAPH[i][j] != 0 && GRAPH[i][j] < MAX) {
					EDGES.add(new Edge(i, j, GRAPH[i][j]));
				}
			}
		}
		Collections.sort(EDGES, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				if (o1.getWeight() == o2.getWeight()) {
					return 0;
				}
				return o1.getWeight() > o2.getWeight() ? 1 : -1;
			}
		});
	}
}
