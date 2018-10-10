package com.nsb.practice.algorithm.tree;

/**
 * 
 * @author Dorae
 *
 */
public class Constants {

	/**
	 * 				0
	 * 		3				1
	 * 	4		5				2
	 */
	public static TreeNode<Integer> ROOT;
	
	static {
		ROOT = new TreeNode<Integer>(0);
		ROOT.setChild(new TreeNode<Integer>(new TreeNode<Integer>(4),new TreeNode<Integer>(5), 3),
				new TreeNode<Integer>(null, new TreeNode<Integer>(2), 1));
	}
}
