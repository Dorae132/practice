package com.nsb.practice.algorithm.find;

import java.util.List;

/**
 * 二分查找
 * 
 * @author Dorae
 *
 */
public class BinarySearch {

	public static void main(String[] args) {
		// Collections.sort(Constants.INTEGER_SEQ);
		// System.out.println(binarySearch(Constants.INTEGER_SEQ, 555));
		// 包装类在不遇到算数运算时不会自动拆箱，equals()不处理数据转型
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		Long g1 = 3L;
		System.out.println(c == d);
		System.out.println(e == f);
		System.out.println(c == (a + b));
		System.out.println(c.equals(a + b));
		System.out.println(g == (a + b));
		System.out.println(g.equals(a + b));
		System.out.println(g == g1);
	}

	private static int binarySearch(List<Integer> list, Integer target) {
		int low = 0;
		int hig = list.size() - 1;
		int mid = -1;
		while (low <= hig) {
			mid = (hig + low) >> 1;
			if (list.get(mid).equals(target)) {
				return mid;
			}
			if (list.get(mid) > target) {
				hig = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}
}
