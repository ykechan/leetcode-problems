package com.leetcode.tomhz.find_the_kth_smallest_sum_of_a_matrix_with_sorted_rows;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Solution {
	public int kthSmallest(int[][] mat, int k) {
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0;i < mat.length;i++) {
			ArrayList<Integer> tmp = new ArrayList<>();
			for(int j = 0;j < mat[0].length;j++) {
				if(i == 0) {
					list.add(mat[i][j]);
					continue;
				}
				for (int preSum : list) {
					tmp.add(preSum + mat[i][j]);
				}
			}
			if(i != 0) {
				list.clear();
				list.addAll(tmp.stream().sorted().limit(k).collect(Collectors.toList()));
			}
		}
		return (int) list.get(k - 1);
    }
}