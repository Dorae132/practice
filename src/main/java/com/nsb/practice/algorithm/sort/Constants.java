package com.nsb.practice.algorithm.sort;

import java.util.List;

import com.google.common.collect.Lists;

/***
 * 
 * @author Dorae
 *
 */
public class Constants {

	public static final List<Integer> INTEGER_SEQ = Lists.newArrayList(1, 89, 231, 432, 23, 234, 123, 345, 645, 765,
			890, 555, 777, 888, 111, 444);

	public static final int[] INT_SEQ = new int[] { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

	public static final int[] INT_ASC = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	/**
	 * 交换元素
	 * 
	 * @param nums
	 * @param i
	 * @param j
	 */
	public static void swap(int[] nums, int i, int j) {
		nums[i] = nums[i] ^ nums[j];
		nums[j] = nums[i] ^ nums[j];
		nums[i] = nums[i] ^ nums[j];
	}
}
