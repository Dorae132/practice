package com.nsb.practice.algorithm.tree;

import java.util.List;
import java.util.Stack;

import com.google.common.collect.Lists;

/**
 * 先序遍历
 * @author Dorae
 *
 */
public class PreorderTraversal<T> {

	public List<T> result = Lists.newArrayList();
	
	public static void main(String[] args) {
		PreorderTraversal<Integer> preorderTraversal = new PreorderTraversal<>();
//		preorderTraversal.recursive(Constants.ROOT, preorderTraversal.result);
		preorderTraversal.nonRecursive(Constants.ROOT, preorderTraversal.result);
		System.out.println(preorderTraversal.result);
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
		result.add(root.value);
		recursive(root.leftChild, result);
		recursive(root.rightChild, result);
		return;
	}
	/**
	 * 非递归
	 * push当前节点 -> push左子树 -> pop左子树 -> push左子树 -> pop右子树
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
		result.add(root.value);
		
		while (!stack.isEmpty()) {
			tmp = stack.peek();
			if (tmp.leftChild != null && tmp.leftChild != lastPop && tmp.rightChild != lastPop) {
				// push left
				stack.push(tmp.leftChild);
				// visit left
				result.add(tmp.leftChild.value);
			} else if (tmp.rightChild != null && tmp.rightChild != lastPop
					&& (tmp.leftChild == null || tmp.leftChild == lastPop)) {
				// push right
				stack.push(tmp.rightChild);
				// visit right
				result.add(tmp.rightChild.value);
			} else {
				// pop
				stack.pop();
				lastPop = tmp;
			}
		}
	}
}
