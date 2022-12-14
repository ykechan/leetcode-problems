package com.leetcode.ykechan.trapping_rain_water_ii;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	
	public int trapRainWater(int[][] heightMap) {
		int nRow = heightMap.length;
		int nCol = heightMap[0].length;
		
		int[] map = this.potential(heightMap, nRow, nCol);
						
		int vol = 0;
		for(int i = 0; i < nRow; i++){
			int off = i * nCol;
			int[] height = heightMap[i];
									
			for(int j = 0; j < nCol; j++){
				vol += map[off + j] - height[j];
			}
		}
		return vol;
	}
	
	protected int[] potential(int[][] heightMap, int nRow, int nCol) {		
		int[] map = new int[nRow * nCol];
		Arrays.fill(map, -1);
		
		PriorityQueue<Tuple> queue = new PriorityQueue<>();
		for(int i = 0; i < nRow; i++) {
			int[] heights = heightMap[i];
			int off = i * nCol;
			if(i > 0 && i < nRow - 1) {
				queue.offer(new Tuple(off, heights[0]));
				queue.offer(new Tuple(off + nCol - 1, heights[nCol - 1]));
				continue;
			}
			
			for(int j = 0; j < nCol; j++){
				queue.offer(new Tuple(off + j, heights[j]));
			}
		}
		
		for(Tuple t : queue) {
			map[t.at] = t.weight;
		}
		
		while(!queue.isEmpty()){
			Tuple t = queue.remove();
			if(map[t.at] < 0) {
				map[t.at] = t.weight;
			}
			
			int i = t.at / nCol;
			int j = t.at % nCol;
			
			if(t.at >= nCol && map[t.at - nCol] < 0){	
				int h = heightMap[i - 1][j];
				queue.offer(new Tuple(t.at - nCol, Math.max(t.weight, h)));
			}
			
			if(t.at + nCol < map.length && map[t.at + nCol] < 0){	
				int h = heightMap[i + 1][j];
				queue.offer(new Tuple(t.at + nCol, Math.max(t.weight, h)));
			}
			
			if(t.at % nCol > 0 && map[t.at - 1] < 0){	
				int h = heightMap[i][j - 1];
				queue.offer(new Tuple(t.at - 1, Math.max(t.weight, h)));
			}
			
			if((t.at + 1) % nCol > 0 && map[t.at + 1] < 0){	
				int h = heightMap[i][j + 1];
				queue.offer(new Tuple(t.at + 1, Math.max(t.weight, h)));
			}
		}
		
		return map;
	}
	
	public static class Tuple implements Comparable<Tuple>{
		
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
