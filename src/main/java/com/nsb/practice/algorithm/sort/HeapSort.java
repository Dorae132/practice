package com.nsb.practice.algorithm.sort;

/**
 * 堆排序
 * @author Dorae
 *
 */
public class HeapSort {

	public static void main(String[] args) {
		heapSort(Constants.INT_ASC, 0, Constants.INT_ASC.length - 1);
		for (int num : Constants.INT_ASC) {
			System.out.print(num + ", ");
		}
	}
	
	public static void heapSort(int[] nums, int start, int end) {
		for (int i = (end-1) >> 1; i >= 0; i--) {// 从最后一个非叶子节点建堆
			adjustHeap(nums, i, end);
		}
		for (int i = 0; i <= end; i++) {
			Constants.swap(nums, start, end - i);// 交换堆顶与当前堆尾的元素
			adjustHeap(nums, start, end - i - 1);
		}
	}
	
	public static void adjustHeap(int[] nums, int low, int high) {
		int i = low, j = (i << 1) + 1;
		while (j <= high) {
			if (j < high && nums[j] < nums[j + 1]) {// 右孩子较大，则指向右孩子
				j++;
			}
			if (nums[i] < nums[j]) {// 将较大值调整到双亲节点
				Constants.swap(nums, i, j);
				i = j;
				j = (i << 1) + 1;
			} else {
				break;
			}
		}
	}
}
