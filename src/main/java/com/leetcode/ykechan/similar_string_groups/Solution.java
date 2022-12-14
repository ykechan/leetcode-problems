package com.leetcode.ykechan.similar_string_groups;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
	
	public int numSimilarGroups(String[] strs) {
		List<int[]> adjList = this.buildGraph(strs);
		
		int numGroups = 0;
		
		int[] group = new int[strs.length];
		Arrays.fill(group, -1);
		
		for(int i = 0; i < strs.length; i++){
			if(group[i] >= 0){
				continue;
			}
			
			int g = numGroups++;
			
			Deque<Integer> stack = new ArrayDeque<>();
			stack.push(i);
			
			while(!stack.isEmpty()){
				int j = stack.pop();
				if(group[j] == g) {
					continue;
				}
				
				if(group[j] >= 0){
					throw new IllegalStateException("Graph is not undirected");
				}
				
				group[j] = g;
				int[] adj = adjList.get(j);
				Arrays.stream(adj).forEach(stack::push);
			}
		}
		return numGroups;
	}
	
	protected List<int[]> buildGraph(String[] strs) {
		int len = strs.length;
		IntArray[] adjList = new IntArray[len];
		
		for(int i = 0; i < len; i++) {
			for(int j = i + 1; j < len; j++){
				if(!this.isSimilar(strs[i], strs[j])) {
					continue;
				}
				
				if(adjList[i] == null){
					adjList[i] = new IntArray();
				}
				
				if(adjList[j] == null){
					adjList[j] = new IntArray();
				}
				
				adjList[i].add(j);
				adjList[j].add(i);
			}
		}
		int[] empty = new int[0];
		return Arrays.stream(adjList).map(a -> a == null ? empty : a.toArray()).collect(Collectors.toList());
	}
	
	protected boolean isSimilar(String s, String t) {
		if(s.length() != t.length()){
			return false;
		}
		
		int p = -1;
		int q = -1;
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == t.charAt(i)){
				continue;
			}
			
			if(p < 0) {
				p = i;
			}else if(q < 0) {
				q = i;
			}else {
				return false;
			}
		}
		return p >= 0 && q >= 0 && s.charAt(p) == t.charAt(q) && s.charAt(q) == t.charAt(p);
	}
	
	protected static class IntArray {
		
		private int[] items;
		
		private int length;
		
		private int step;
		
		public IntArray() {
			this.items = new int[8];
			this.length = 0;
			this.step = 5;
		}
		
		public void add(int item) {
			this.ensureCapacity(this.length + 1);
			this.items[this.length++] = item;
		}
		
		public int[] toArray() {
			return Arrays.copyOf(this.items, this.length);
		}
		
		protected void ensureCapacity(int target) {
			int len = this.items.length;
			int delta = this.step;
			while(len < target) {
				int temp = len;
				len += delta;
				delta = temp;
			}
			this.step = delta;
			this.items = Arrays.copyOf(this.items, len);
		}
		
	}

}
