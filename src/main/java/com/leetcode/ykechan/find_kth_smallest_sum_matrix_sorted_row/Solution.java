package com.leetcode.ykechan.find_kth_smallest_sum_matrix_sorted_row;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
	
	public int kthSmallest(int[][] mat, int k) {
		int sum = Arrays.stream(mat).mapToInt(a -> a[0]).sum();
		if(k < 2) {
			return sum;
		}
		
		Set<int[]> done = new TreeSet<>((a, b) -> {
			int len = Math.min(a.length, b.length);
			for(int i = 0; i < len; i++) {
				int m = a[i];
				int n = b[i];
				if(m < n) {
					return -1;
				}
				
				if(m > n) {
					return 1;
				}
			}
			return a.length < b.length ? -1 : a.length > b.length ? 1 : 0;
		});
		
		PriorityQueue<Tuple> queue = new PriorityQueue<>(Comparator.comparingInt(t -> t.sum));
		queue.offer(new Tuple(new int[mat.length], sum));
		for(int n = 1; n < k; n++) {
			Tuple t = queue.remove();
			int[] array = t.array;
			int curr = t.sum;
			System.out.println("#" + n + ": " + Arrays.toString(array)  + " => " + curr);
			
			for(int i = 0; i < mat.length; i++) {
				int[] row = mat[i];
				if(array[i] + 1 < row.length) {
					int[] temp = Arrays.copyOf(array, array.length);
					int j = array[i];
					int next = curr + row[j + 1] - row[j];
					temp[i] = j + 1;
					if(!done.contains(temp)) {
						queue.offer(new Tuple(temp, next));
						done.add(temp);
					}
				}				
			}
		}
		Tuple t = queue.remove();
		return t.sum;
	}	
	
	public static class Tuple {
		
		public final int[] array;
		
		public final int sum;

		public Tuple(int[] array, int sum) {
			super();
			this.array = array;
			this.sum = sum;
		}
		
	}

}
