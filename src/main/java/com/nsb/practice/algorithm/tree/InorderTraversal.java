package com.nsb.practice.algorithm.tree;

import java.util.List;
import java.util.Stack;

import org.springframework.data.redis.listener.Topic;

import com.google.common.collect.Lists;

public class InorderTraversal<T> {
	
	public List<T> result = Lists.newArrayList();
	
	public static void main(String[] args) {
		InorderTraversal<Integer> inorderTraversal = new InorderTraversal<>();
//		inorderTraversal.recursive(Constants.ROOT, inorderTraversal.result);
		inorderTraversal.nonRecursive(Constants.ROOT, inorderTraversal.result);
		System.out.println(inorderTraversal.result);
	}
	
	/**
	 * 递归
	 * @param root
	 * @return
	 */
	private void recursive(TreeNode<T> root, List<T> result) {
		if (root == null) {
			return;
		}
		recursive(root.leftChild, result);
		result.add(root.value);
		recursive(root.rightChild, result);
		return;
	}
	/**
	 * 非递归
	 * push当前节点 -> push左子树 -> pop左子树 -> push右子树 -> pop右子树
	 * @param root
	 * @param result
	 */
	private void nonRecursive(TreeNode<T> root, List<T> result) {
		if (root == null) {
			return;
		}
		// 用于模拟递归
		Stack<TreeNode<T>> stack = new Stack<>();
		TreeNode<T> lastPop = root;
		TreeNode<T> tmp = null;
		// push当前节点
		stack.push(root);
		
		while (!stack.isEmpty()) {
			tmp = stack.peek();
			if (tmp.leftChild != null && tmp.leftChild != lastPop && tmp.rightChild != lastPop) {
				// push left
				stack.push(tmp.leftChild);
			} else if (tmp.rightChild != null && tmp.rightChild != lastPop
					&& (tmp.leftChild == null || tmp.leftChild == lastPop)) {
				// push right
				stack.push(tmp.rightChild);
				// visit top
				result.add(tmp.value);
			} else {
				// pop
				stack.pop();
				lastPop = tmp;
				if (tmp.rightChild == null) {
					// visit right
					result.add(tmp.value);
				}
			}
		}
	}
}
