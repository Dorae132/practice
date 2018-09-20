package com.nsb.practice.algorithm.graphic;

import java.util.Stack;

/**
 * 拓扑排序
 * 
 * @author Dorae
 *
 */
public class Topology {

	public static void main(String[] args) {
		topology(Constants.DIRECTED_GRAPH_HEADS, Constants.DIRECTED_GRAPH_HEADS.length);
	}

	static void topology(HeadNode[] headNodes, int n) {
		Stack<HeadNode> stack = new Stack<>();
		TableNode nextNode = null;
		HeadNode node = null;
		for (int i = 0; i < n; i++) {
			// 寻找入度为0的headNode
			if (headNodes[i].getInArcCount() == 0) {// 入度为0
				stack.push(headNodes[i]);
			}
		}
		while (!stack.isEmpty()) {
			// 出栈
			HeadNode headNode = stack.pop();
			System.out.print("<-" + headNode.getData());
			nextNode = headNode.getFirstArc();
			while (nextNode != null) {
				node = headNodes[nextNode.getData() - 1];
				node.setInArcCount(node.getInArcCount() - 1);
				if (node.getInArcCount() == 0) {
					stack.push(node);
				}
				nextNode = nextNode.getNext();
			}
		}
	}
}
