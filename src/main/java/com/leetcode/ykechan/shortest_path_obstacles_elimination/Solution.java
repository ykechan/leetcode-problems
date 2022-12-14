package com.leetcode.ykechan.shortest_path_obstacles_elimination;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	
	public int shortestPath(int[][] grid, int k) {
		int nCol = grid[0].length;
		
		String str = this.toString(grid);
		
		int[] used = new int[str.length()];
		Arrays.fill(used, 1 + k);
				
		int min = -1;
		
		PriorityQueue<Trace> queue = new PriorityQueue<>();
		queue.add(new Trace(0, 0, 0));
		
		while(!queue.isEmpty()){
			Trace tr = queue.remove();
			//System.out.println("@" + (tr.at % nCol) + "," + (tr.at / nCol) + ", steps=" + tr.steps + ", work=" + tr.work);
			if(tr.work >= used[tr.at]){
				//System.out.println("Easier path found using " + used[tr.at]);
				continue;
			}
			
			if(tr.at == str.length() - 1){
				min = min < 0 ? tr.steps : Math.min(tr.steps, min);
			}
			
			used[tr.at] = tr.work;
			Trace[] neighbors = this.move(tr, str, nCol, k);
			for(Trace n : neighbors) {
				if(n == null){
					continue;
				}
				
				queue.add(n);
			}
		}
		return min;
	}
	
	protected Trace[] move(Trace tr, String str, int nCol, int k) {
		int pos = tr.at;
		int[] dir = new int[] {
			pos % nCol > 0 ? -1 : 0, 
			pos % nCol < nCol - 1 ? 1 : 0, 
			pos >= nCol ? -nCol : 0, 
			pos + nCol < str.length() ? nCol : 0
		};
		
		Trace[] mv = new Trace[dir.length];
		for(int i = 0; i < dir.length; i++){
			int v = dir[i];
			if(v == 0){
				continue;
			}
			
			int at = pos + v;
			char ch = str.charAt(at);
			if(ch != ' ' && tr.work >= k){
				continue;
			}
			
			mv[i] = new Trace(at, tr.steps + 1, tr.work + (ch == ' ' ? 0 : 1));
		}
		return mv;
	}
	
	protected String toString(int[][] grid) {
		int nRow = grid.length;
		int nCol = grid[0].length;
		
		StringBuilder buf = new StringBuilder();
		for(int i = 0; i < nRow; i++) {
			int[] row = grid[i];
			if(row.length != nCol){
				throw new IllegalArgumentException();
			}
			
			for(int j = 0; j < nCol; j++) {
				char ch = row[j] > 0 ? 'X' : ' ';
				buf.append(ch);
			}
		}
		
		return buf.toString();
	}
	
	protected static class Trace implements Comparable<Trace> {
		
		public final int at;
		
		public final int steps;
		
		public final int work;

		public Trace(int at, int steps, int work) {
			this.at = at;
			this.steps = steps;
			this.work = work;
		}

		@Override
		public int compareTo(Trace t) {
			int cmp = this.steps < t.steps ? -1 : this.steps > t.steps ? 1 : 0;
			if(cmp == 0){
				cmp = this.work < t.work ? -1 : this.work > t.work ? 1 : 0;
			}
			return cmp;
		}
		
	}

}
