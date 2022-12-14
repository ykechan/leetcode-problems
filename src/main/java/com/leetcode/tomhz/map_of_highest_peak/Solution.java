package com.leetcode.tomhz.map_of_highest_peak;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
	class Point{
		public int x;
		public int y;
		public int height;
		public Point(int i, int j,int height) {
			// TODO Auto-generated constructor stub
			this.x = i;
			this.y = j;
			this.height = height;
		}
	}
	
	public int[][] highestPeak(int[][] isWater){
		int[][] rs = new int[isWater.length][isWater[0].length];
		Arrays.stream(rs).forEach(a -> Arrays.fill(a, -1));
		Deque<Point> stack = new ArrayDeque<>();
		//find water point
		for(int x = 0;x < isWater.length;x++) {
			for(int y = 0; y < isWater[x].length;y++) {
				if(isWater[x][y] == 1) {
					stack.push(new Point(x,y,0));
					rs[x][y] = 0;
				}
			}
		}
		
		while(!stack.isEmpty()) {
			Point current = stack.pollLast();
			List<Point> nextList = findNextList(rs,current);
			nextList.stream().forEach(p -> {
				rs[p.x][p.y] = p.height;
				stack.push(p);
			});
		}
		
		return rs;
	}
	
	public List<Point> findNextList(int[][] rs,Point current){
		List<Point> nextList = new ArrayList<>();
		int[][] dir = new int[][] {
			new int[] {0, -1},//north
			new int[] {1, 0},//east
			new int[] {0, 1},//south
			new int[] {-1, 0},//west
		};
		int height = current.height + 1;
		for(int dirIdx = 0;dirIdx < dir.length;dirIdx++) {
			int x = current.x + dir[dirIdx][0];
			int y = current.y + dir[dirIdx][1];
			
			if(x < 0 || x > (rs.length - 1)) {
				continue;
			}
			if(y < 0 || y > (rs[0].length - 1)) {
				continue;
			}
			if(rs[x][y] != -1) {
				continue;
			}
			nextList.add(new Point(x,y,height));
		}
		return nextList;
	}

}