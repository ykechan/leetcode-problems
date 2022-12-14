package com.leetcode.ykechan.min_cost_to_connect_2_groups_of_points;

import java.util.Arrays;
import java.util.List;

public class Solution {
	
	public int connectTwoGroups(List<List<Integer>> cost) {
		int[] matrix = this.toMatrix(cost);
		int nRow = cost.size();
		int nCol = cost.get(0).size();
		
		int len = (nRow + 1) * (1 << nCol);
		
		int[] mem = new int[len];
		Arrays.fill(mem, -1);
		
		return this.match(matrix, nCol, 0, mem);
	}
	
	protected int match(int[] matrix, int nCol, int hash, int[] mem) {
		if(mem[hash] >= 0) {
			return mem[hash];
		}
		int nRow = matrix.length / nCol;
		
		int mod = 1 << nCol;
		int begin = hash / mod;
		int mask = hash % mod;				
		
		if(begin >= nRow){
			int cost = 0;
			for(int i = 0; i < nCol; i++){
				int m = (mask >> i) % 2;
				if(m > 0){
					continue;
				}
						
				int min = -1;
				for(int j = 0; j < nRow; j++) {
					int weight = matrix[j * nCol + i];
					if(min < 0 || weight < min){
						min = weight;
					}
				}
				cost += min;
			}
			System.out.println("begin=" + begin + ", mask=" + mask + ", cost=" + cost + ", *");
			mem[hash] = cost;
			return cost;
		}
		
		int off = begin * nCol;
		int min = -1;
		for(int i = 0; i < nCol; i++){
			int weight = matrix[off + i];
			int cost = weight + this.match(matrix, nCol, (begin + 1) * mod + (mask | 1 << i), mem);
			
			if(min < 0 || cost < min){
				min = cost;
			}
		}
		
		System.out.println("begin=" + begin + ", mask=" + mask + ", cost=" + min);
		mem[hash] = min;
		return min;
	}
	
	protected int[] toMatrix(List<List<Integer>> cost) {
		int nRow = cost.size();
		int nCol = cost.get(0).size();
		
		int[] matrix = new int[nRow * nCol];
		for(int i = 0; i < cost.size(); i++) {
			List<Integer> row = cost.get(i);
			if(row.size() != nCol) {
				throw new IllegalArgumentException();
			}
			
			int off = i * nCol;
			for(int j = 0; j < row.size(); j++) {
				matrix[off + j] = row.get(j);
			}
		}
		return matrix;
	}

}
