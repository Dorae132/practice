package com.nsb.practice.algorithm.sort;

/**
 * 归并
 * @author Dorae
 *
 */
public class MergeSort {

	public static void main(String[] args) {
		sort(Constants.INT_SEQ, 0, Constants.INT_SEQ.length - 1, new int[Constants.INT_SEQ.length]);
		for (int e : Constants.INT_SEQ) {
			System.out.print(e + ", ");
		}
	}

	private static void sort(int[] data, int left, int right, int[] temp) {
		if (left < right) {
			int mid = (left + right) >> 1;
			sort(data, left, mid, temp);
			sort(data, mid + 1, right, temp);
			merge(data, left, right, mid, temp);
		}
	}
	
	private static void merge(int[] data, int left, int right, int mid, int[] temp) {
		int i = left;
		int j = mid + 1;
		int t = 0;
		while (i <= mid && right >= j) {
			if (data[i] <= data[j]) {
				temp[t++] = data[i++];
			} else {
				temp[t++] = data[j++];
			}
		}
		
		while (i <= mid) {
			temp[t++] = data[i++];
		}
		while (right >= j) {
			temp[t++] = data[j++];
		}
		
		t = 0;
		while (left <= right) {
			data[left++] = temp[t++];
		}
	}
}
