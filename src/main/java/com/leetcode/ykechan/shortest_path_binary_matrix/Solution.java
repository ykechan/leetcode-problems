package com.leetcode.ykechan.shortest_path_binary_matrix;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	
	public int shortestPathBinaryMatrix(int[][] grid) {
		int height = grid.length;
		int width = grid[0].length;
		
		PriorityQueue<Tuple> queue = new PriorityQueue<>();
		if(grid[0][0] == 0) {
			queue.add(new Tuple(0, 1));
		}
		
		int[] dists = new int[width * height];
		Arrays.fill(dists, -1);
		
		int len = dists.length;
		
		while(!queue.isEmpty()){
			Tuple t = queue.poll();
			if(dists[t.value] >= 0 && dists[t.value] <= t.weight) {
				continue;
			}
			
			dists[t.value] = t.weight;
			if(dists[len - 1] >= 0){
				break;
			}
			
			int i = t.value / width;
			int j = t.value % width;
			
			for(int y = -1; y < 2; y++){
				if(i + y < 0 || i + y >= height){
					continue;
				}
				
				for(int x = -1; x < 2; x++){
					int n = (i + y) * width + (j + x);
					if(j + x < 0 || j + x >= height || grid[i + y][j + x] != 0 || dists[n] >= 0) {
						continue;
					}
					
					queue.add(new Tuple(n, t.weight + 1));
				}
			}
		}
		return dists[len - 1];
	}
	
	public static class Tuple implements Comparable<Tuple> {
		
		public final int value, weight;

		public Tuple(int value, int weight) {
			this.value = value;
			this.weight = weight;
		}

		@Override
		public int compareTo(Tuple t) {
			return this.weight < t.weight ? -1 : this.weight > t.weight ? 1 : 0;
		}
		
	}

}
