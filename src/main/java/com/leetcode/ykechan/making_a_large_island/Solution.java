package com.leetcode.ykechan.making_a_large_island;

import java.util.Arrays;

public class Solution {
	
	public int largestIsland(int[][] grid) {
		int nRow = grid.length;
		int nCol = grid[0].length;
		
		int len = nRow * nCol;
		boolean[] array = this.toBoolArray(grid);
		
		DisjointSet set = new DisjointSet(len);
		
		for(int i = 0; i < array.length; i++) {
			if(!array[i]) {
				continue;
			}
			
			if(i >= nCol && array[i - nCol]){
				set.union(i, i - nCol);
			}
			
			if(i + nCol < len && array[i + nCol]) {
				set.union(i, i + nCol);
			}
			
			if(i % nCol > 0 && array[i - 1]) {
				set.union(i, i - 1);
			}
			
			if(i % nCol < nCol - 1 && array[i + 1]) {
				set.union(i, i + 1);
			}
		}
		
		int[] area = new int[len];
		Arrays.fill(area, 0);
		
		for(int i = 0; i < array.length; i++){
			if(!array[i]){
				continue;
			}
			
			int root = set.find(i);
			area[root]++;
		}
		
		int max = -1;
		for(int i = 0; i < array.length; i++){
			if(array[i]){
				int a = set.find(i);
				if(max < 0 || max < area[a]) {					
					max = area[a];
					System.out.println("max @ " + i + " => " + max);
				}
				continue;
			}					
			
			int u = i % nCol > 0 && array[i - 1] ? set.find(i - 1) : -1;
			int v = i % nCol < nCol - 1 && array[i + 1] ? set.find(i + 1) : -1;
			int x = i >= nCol && array[i - nCol] ? set.find(i - nCol) : -1;
			int y = i + nCol < len && array[i + nCol] ? set.find(i + nCol) : -1;
			
			int merge = 1
				+ (u < 0 ? 0 : area[u])
				+ (v < 0 || v == u ? 0 : area[v])
				+ (x < 0 || x == u || x == v ? 0 : area[x])
				+ (y < 0 || y == u || y == v || y == x ? 0 : area[y]);
			
			if(merge > max) {
				max = merge;
			}
		}
		return max;
	}
	
	protected boolean[] toBoolArray(int[][] grid) {
		int nRow = grid.length;
		int nCol = grid[0].length;
		
		int len = nRow * nCol;
		boolean[] array = new boolean[len];
		for(int i = 0; i < nRow; i++) {
			int[] row = grid[i];
			if(row.length != nCol) {
				throw new IllegalArgumentException();
			}
			
			int off = i * nCol;
			for(int j = 0; j < nCol; j++) {
				array[off + j] = row[j] > 0;
			}
		}
		return array;
	}
	
	protected static class DisjointSet {
		
		private int[] parent;
		
		private int[] heights;

		public DisjointSet(int len) {
			this.parent = new int[len];
			this.heights = new int[len];
			
			for(int i = 0; i < len; i++) {
				this.parent[i] = i;
				this.heights[i] = 1;
			}
		}
		
		public int find(int element) {
			int curr = element;
			while(this.parent[curr] != curr){
				curr = this.parent[curr];
			}
			return curr;
		}
		
		public int union(int a, int b) {
			int x = this.find(a);
			int y = this.find(b);
			
			int h0 = this.heights[x];
			int h1 = this.heights[y];
			
			if(h0 > h1){
				this.parent[y] = x;
				return x;
			} 
			
			this.parent[x] = y;
			this.heights[y] += h0 == h1 ? 1 : 0;
			return y;
		}
		
	}

}
