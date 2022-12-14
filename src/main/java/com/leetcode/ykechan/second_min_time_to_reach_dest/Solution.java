package com.leetcode.ykechan.second_min_time_to_reach_dest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Solution {
	
	public int secondMinimum(int n, int[][] edges, int time, int change) {
		int[] dest = new int[1 + n];
		Arrays.fill(dest, 0);
		
		int[] dist = new int[1 + n];
		Arrays.fill(dist, -1);
		
		List<int[]> adjList = this.toAdjList(n, edges);
		
		PriorityQueue<Trace> queue = new PriorityQueue<>(Comparator.comparingInt(t -> t.time));
		queue.add(new Trace(-1, 1, 0));
		
		while(!queue.isEmpty()){
			Trace tr = queue.remove();
			//System.out.println("@" + tr.from + "=>" + tr.at + ", t=" + tr.time + ", count=" +dest[tr.at]);
			if(tr.at == n && dest[n] == 1 && tr.time > dist[n]){
				return tr.time;
			}
			
			int prevTime = dist[tr.at];
			if(dist[tr.at] < 0) {
				dest[tr.at] = 1;
				dist[tr.at] = tr.time;
			}else if(tr.time > prevTime) {
				dest[tr.at]++;
				dist[tr.at] = tr.time;
			}
			
			if(dest[tr.at] > 2 || tr.time <= prevTime) {
				continue;
			}
			
			int wait = (tr.time / change) % 2 < 1 ? 0 : change - (tr.time % change);
			int t1 = tr.time + wait;
			
			int[] neighbours = adjList.get(tr.at);
			for(int i : neighbours){
				queue.add(new Trace(tr.at, i, t1 + time));
			}
		}
		return -1;
	}
	
	protected List<int[]> toAdjList(int n, int[][] edges) {
		IntArray[] adjList = new IntArray[1 + n];
		for(int[] e : edges) {
			int u = e[0];
			int v = e[1];
			
			if(adjList[u] == null){
				adjList[u] = new IntArray();
			}
			
			if(adjList[v] == null){
				adjList[v] = new IntArray();
			}
			
			adjList[u].add(v);
			adjList[v].add(u);
		}
		return Arrays.stream(adjList).map(a -> a == null ? new int[0] : a.toArray()).collect(Collectors.toList());
	}
	
	protected static class Trace {
		
		public final int from, at, time;

		public Trace(int from, int at, int time) {
			this.from = from;
			this.at = at;
			this.time = time;
		}
		
	}
	
	protected static class IntArray {
		
		private int num, step;
		
		private int[] elements;

		public IntArray() {
			this.elements = new int[8];
			this.num = 0;
			this.step = 5;
		}
		
		public IntArray add(int elem) {
			this.ensureCapacity(1 + this.num);
			this.elements[this.num++] = elem;
			return this;
		}
		
		public int[] toArray() {
			return Arrays.copyOf(this.elements, this.num);
		}
		
		protected void ensureCapacity(int target) {
			int len = this.elements.length;
			int stride = this.step;
			while(len < target) {
				int temp = len;
				len += stride;
				stride = temp;
			}
			
			if(len <= this.elements.length) {
				return;
			}
			
			int[] elements = Arrays.copyOf(this.elements, len);
			this.elements = elements;
			this.step = stride;			
		}
		
	}

}
