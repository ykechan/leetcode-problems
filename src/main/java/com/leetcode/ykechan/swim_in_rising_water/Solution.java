package com.leetcode.ykechan.swim_in_rising_water;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	
	public int swimInWater(int[][] grid) {
		int nRow = grid.length;
		int nCol = grid[0].length;
		
		int[] cost = new int[nRow * nCol];
		Arrays.fill(cost, -1);
		
		PriorityQueue<Tuple> heap = new PriorityQueue<>();
		heap.add(new Tuple(0, grid[0][0]));
		
		while(!heap.isEmpty()){
			Tuple t = heap.poll();
			if(cost[t.at] >= 0) {
				continue;
			}
			
			cost[t.at] = t.weight;
			if(t.at == cost.length - 1){
				break;
			}
			
			int i = t.at / nCol;
			int j = t.at % nCol;
			
			if(i > 0){
				heap.add(new Tuple(t.at - nCol, Math.max(t.weight, grid[i - 1][j])));
			}
			
			if(i + 1 < nRow){
				heap.add(new Tuple(t.at + nCol, Math.max(t.weight, grid[i + 1][j])));
			}
			
			if(j > 0){
				heap.add(new Tuple(t.at - 1, Math.max(t.weight, grid[i][j - 1])));
			}
			
			if(j + 1 < nCol){
				heap.add(new Tuple(t.at + 1, Math.max(t.weight, grid[i][j + 1])));
			}
		}
				
		return cost[cost.length - 1];
	}
	
	public static class Tuple implements Comparable<Tuple> {
		
		public final int at, weight;

		public Tuple(int at, int weight) {
			this.at = at;
			this.weight = weight;
		}

		@Override
		public int compareTo(Tuple t) {
			return this.weight < t.weight ? -1 : this.weight > t.weight ? 1 : 0;
		}
		
	}

}
