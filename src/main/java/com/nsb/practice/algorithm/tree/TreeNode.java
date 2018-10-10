package com.nsb.practice.algorithm.tree;

/**
 * 节点
 * @author Dorae
 *
 */
public class TreeNode<T> {

	public TreeNode<T> leftChild;
	
	public TreeNode<T> rightChild;
	
	public T value;

	public TreeNode(TreeNode leftChild, TreeNode rightChild, T value) {
		super();
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.value = value;
	}

	public TreeNode(T value) {
		super();
		this.value = value;
	}
	
	public void setChild(TreeNode left, TreeNode right) {
		this.leftChild = left;
		this.rightChild = right;
	}
}
