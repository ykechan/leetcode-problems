package com.leetcode.tomhz_shortest_path_in_binary_matrix;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
	class Path{
		public int x;
		public int y;
		public int lenth;
		public Path(int i, int j,int length) {
			// TODO Auto-generated constructor stub
			this.x = i;
			this.y = j;
			this.lenth = length;
		}
	}
	
	public int shortestPathBinaryMatrix(int[][] grid) {
		boolean[][] visited = new boolean[grid.length][grid[0].length];
		visited[0][0] = true;
		
		if(grid[0][0] == 1) {
			return -1;
		}
		if(grid[0][0] == 0 && grid.length == 1 && grid[0].length == 1) {
			return 1;
		}
		AtomicInteger shortest = new AtomicInteger(-1);
		Deque<Path> stack = new ArrayDeque<>();
		stack.push(new Path(0,0,1));
		
		while(!stack.isEmpty()) {
			Path current = stack.pollLast();
			int x = current.x, y = current.y;
			List<Path> clearWays = findClearWay(grid,visited,x,y);
			clearWays.stream().forEach(path -> {

				int newLength = current.lenth + 1;
				if(path.x == grid.length - 1 && path.y == grid[0].length - 1) {
					shortest.set(newLength);
					stack.clear();
					return;
				}
				
				stack.push(new Path(path.x,path.y,newLength));
			});
		}
		
		return shortest.get();
	}
	
	private List<Path> findClearWay(int[][] grid,boolean[][] visited, int x, int y) {
		// TODO Auto-generated method stub
		List<Path> rs = new ArrayList<>();
		for(int a = x - 1;a <= x + 1;a++) {
			for(int b = y - 1;b <= y + 1;b++) {
				if(a < 0 || b < 0) {
					continue;
				}
				if(a > (grid.length - 1) || b > (grid[0].length - 1)) {
					continue;
				}
				if(visited[a][b]) {
					continue;
				}
				if(grid[a][b] == 0) {
					rs.add(new Path(a,b,0));
					visited[a][b] = true;
				}
			}
		}
		return rs;
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		Solution s = new Solution();
		System.out.println(s.shortestPathBinaryMatrix(new int[][] {
			new int[] {0, 1},
			new int[] {1, 0}
		}));
		
		System.out.println(s.shortestPathBinaryMatrix(new int[][] {
			new int[] {0,0,0},
			new int[] {1,1,0},
			new int[] {1,1,0}
		}));
		
		System.out.println(s.shortestPathBinaryMatrix(new int[][] {
			new int[] {1,0,0},
			new int[] {1,1,0},
			new int[] {1,1,0}
		}));
		
		System.out.println(s.shortestPathBinaryMatrix(new int[][] {
			new int[] {0,0,0},
			new int[] {1,1,0},
			new int[] {1,1,1}
		}));

	}
}