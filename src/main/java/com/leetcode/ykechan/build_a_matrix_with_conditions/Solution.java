package com.leetcode.ykechan.build_a_matrix_with_conditions;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
	
	public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
		int[] rows = this.topSort(k, rowConditions);
		int[] cols = this.topSort(k, colConditions);
		
		if(rows == null || cols == null) {
			return new int[][] {};
		}
		
		int[] asgnRow = new int[1 + k];		
		for(int i = 0; i < rows.length; i++) {
			int num = rows[i];
			asgnRow[num] = i;
		}
		
		int[] asgnCol = new int[1 + k];
		for(int j = 0; j < cols.length; j++) {
			int num = cols[j];
			asgnCol[num] = j;
		}
		
		int[][] matrix = new int[k][k];
		for(int n = 1; n <= k; n++){
			int i = asgnRow[n];
			int j = asgnCol[n];
			matrix[i][j] = n;
		}
		return matrix;
	}	
	
	protected int[] topSort(int k, int[][] conditions) {
		List<int[]> adjList = this.toAdj(k, conditions);	
		
		Flag[] flags = new Flag[1 + k];
		
		IntArray result = new IntArray(); 
		
		for(int i = 1; i <= k; i++){
			if(flags[i] == Flag.VISITED){
				continue;
			}
			
			if(flags[i] == Flag.MARKED){
				throw new IllegalStateException();
			}
			
			Deque<Integer> stack = new ArrayDeque<>();
			stack.push(i);
			
			while(!stack.isEmpty()){
				int j = stack.peek();
				if(flags[j] == Flag.VISITED) {
					stack.pop();
					continue;
				}
				
				if(flags[j] == Flag.MARKED){
					// done
					flags[j] = Flag.VISITED;
					stack.pop();
					result.add(j);
					continue;
				}
				
				flags[j] = Flag.MARKED;
				int[] adj = adjList.get(j);
				for(int n : adj) {
					if(flags[n] == Flag.VISITED) {
						continue;
					}
					
					if(flags[n] == Flag.MARKED) {
						// cyclic dependency
						return null;
					}
					
					stack.push(n);
				}
			}
		}
		
		return result.toArray();
	}
	
	protected List<int[]> toAdj(int k, int[][] conditions) {
		IntArray[] adjMap = new IntArray[1 + k];
		for(int[] condition : conditions){
			int lower = condition[0];
			int upper = condition[1];
			
			IntArray adj = adjMap[upper];
			if(adj == null){
				adj = new IntArray();
				adjMap[upper] = adj;
			}
			adj.add(lower);
		}
		return Arrays.stream(adjMap)
			.map(a -> a == null ? new int[0] : a.toArray())
			.collect(Collectors.toList());
	}
	
	protected enum Flag {
		NEW, MARKED, VISITED
	}
	
	protected static class IntArray {
		
		private int[] elements;
		
		private int num;
		
		private int stride;
		
		public IntArray() {
			this(new int[8], 0, 5);
		}
		
		protected IntArray(int[] elements, int num, int stride) {
			this.elements = elements;
			this.num = num;
			this.stride = stride;
		}

		public void add(int elem) {
			this.ensureCapacity(this.num + 1);
			this.elements[this.num++] = elem;
		}
		
		public int[] toArray() {
			return Arrays.copyOfRange(this.elements, 0, this.num);
		}
		
		private void ensureCapacity(int target) {
			int len = this.elements.length;
			while(len < target) {
				int temp = len;
				len += this.stride;
				this.stride = temp;
			}
			
			if(len <= this.elements.length) {
				return;
			}
			
			this.elements = Arrays.copyOf(this.elements, len);
		}
		
	}

}
