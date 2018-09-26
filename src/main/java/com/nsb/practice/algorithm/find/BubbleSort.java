package com.nsb.practice.algorithm.find;

/**
 * 冒泡排序
 * 
 * @author Dorae
 *
 */
public class BubbleSort {

	public static void main(String[] args) {
		int[] nums = bubbleSort(Constants.INT_SEQ, 0, Constants.INT_SEQ.length - 1);
		for (int i : nums) {
			System.out.print(i + ", ");
		}
	}

	public static int[] bubbleSort(int[] nums, int start, int end) {
		int flag = 0;
		for (int i = start; i <= end; i++) {
			for (int j = end; j > i; j--) {
				if (nums[j] < nums[j - 1]) {// 交换
					nums[j] = nums[j] ^ nums[j - 1];
					nums[j - 1] = nums[j] ^ nums[j - 1];
					nums[j] = nums[j] ^ nums[j - 1];
					flag++;
				}
			}
			if (flag == 0) {
				break;
			}
		}
		return nums;
	}
}
