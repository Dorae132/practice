package com.nsb.practice.algorithm.sort;

/**
 * 直接插入排序
 * @author Dorae
 *
 */
public class DirectInsertSort {

	public static void main(String[] args) {
		int[] num = directInsertSort(Constants.INT_SEQ, 9, Constants.INT_SEQ.length - 1);
		for (int i : num) {
			System.out.print(i + ", ");
		}
	}
	
	/**
	 * start -> end 排序
	 * @param num
	 * @param start
	 * @param end
	 */
	public static int[] directInsertSort(int[] num, int start, int end) {
		int tmp = 0;
		int j = start;
		for (int i = start; i <= end; i++) {
			tmp = num[i];
			// 找出tmp的位置
			for (j = i-1; j >= start; j--) {
				if (num[j] > tmp) {
					// 后移>j的元素
					num[j + 1] = num[j];
				}
			}
			j = j >= start ? j : start;
			num[j] = tmp;
		}
		return num;
	}
}