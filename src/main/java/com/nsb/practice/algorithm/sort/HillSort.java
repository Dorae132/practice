package com.nsb.practice.algorithm.sort;

/**
 * 希尔排序
 * @author Dorae
 *
 */
public class HillSort {

	public static void main(String[] args) {
		int[] nums = hillSort(Constants.INT_SEQ, 0, 10, 1);
		for (int i : nums) {
			System.out.print(i + ",");
		}
	}
	
	/**
	 * @param nums
	 * @param start
	 * @param end
	 * @param dis0 初始组长度
	 */
	private static int[] hillSort(int[] nums, int start, int end, int dis0) {

		// 缩小增量
		while (dis0 > 0) {
			// 从第dis个元素，对其所在组进行直接插入排序
			for (int i = start + dis0; i <= end; i ++) {
				int j = i;
				while (j - dis0 >= start) {
					if (nums[j] < nums[j - dis0]) {
						nums[j] = nums[j] ^ nums[j - dis0];
						nums[j - dis0] = nums[j] ^ nums[j - dis0];
						nums[j] = nums[j] ^ nums[j - dis0];
					}
					j = j - dis0;
				}
			}
			dis0 = dis0 >> 1;
		}
		return nums;
	}
	
}
