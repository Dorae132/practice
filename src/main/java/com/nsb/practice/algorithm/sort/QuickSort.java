package com.nsb.practice.algorithm.sort;

/**
 * 快排
 * @author Dorae
 *
 */
public class QuickSort {

	public static void main(String[] args) {
		quickSort(Constants.INT_SEQ, 0, Constants.INT_SEQ.length - 1);
		for (int num : Constants.INT_SEQ) {
			System.out.print(num + ", ");
		}
	}
	
	public static void quickSort(int[] nums, int start, int end) {
		if (end <= start) {
			return;
		}
		int i = start, j = end;
		// 区间第一个记录作为基准
		int seed = nums[start];
		while(i < j) {
			// 找到右边比seed小的元素
			while (i < j && nums[j] > seed) {
				j--;
			}
			if (i < j) {
				nums[i] = nums[j];
				i++;
			}
			// 找到左边比seed大的元素
			while (i < j && nums[i] < seed) {
				i++;
			}
			if (i < j) {
				nums[j] = nums[i];
				j--;
			}
		}
		nums[i] = seed;
		quickSort(nums, start, i - 1);
		quickSort(nums, i + 1, end);
	}
}
