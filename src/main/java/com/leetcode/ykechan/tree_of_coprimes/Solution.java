package com.leetcode.ykechan.tree_of_coprimes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import com.leetcode.ykechan.util.AvlTree.AvlNode;

public class Solution {
	
	private static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23};
	
	public int[] getCoprimes(int[] nums, int[][] edges) {
		PriorityQueue<Trace> heap = new PriorityQueue<>(Comparator.comparingInt(t -> t.dist));
		heap.add(new Trace(0, 0));
		
		int[] factors = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
			int num = nums[i];
			int hash = 0;
			for(int p : PRIMES) {
				hash *= 2;
				if(num % p == 0) {
					hash |= 1;
				}
			}
			factors[i] = hash;
		}
		
		List<int[]> adjList = this.toAdjList(edges, nums.length);
		
		int[] parent = new int[nums.length];
		Arrays.fill(parent, -1);
		
		while(!heap.isEmpty()){
			Trace tr = heap.remove();
			int[] adj = adjList.get(tr.node);
			for(int n : adj) {
				if(parent[n] >= 0) {
					continue;
				}
				
				parent[n] = tr.node;
				heap.add(new Trace(n, tr.dist + 1));
			}
		}
		
		int[] ans = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
			int p = i;
			int num = nums[i];
			while(parent[p] >= 0) {
				p = parent[p];
				if((factors[p] & factors[num]) == 0) {
					ans[i] = p;
					break;
				}
			}
		}
		return ans;
	}
	
	protected List<int[]> toAdjList(int[][] edges, int n) {
		IntArray[] adj = new IntArray[n];
		for(int[] e : edges) {
			int i = e[0];
			int j = e[1];
			
			if(adj[i] == null){
				adj[i] = new IntArray();
			}
			
			if(adj[j] == null){
				adj[j] = new IntArray();
			}
			
			adj[i].add(j);
			adj[j].add(i);
		}
		return Arrays.stream(adj).map(a -> a == null ? new int[0] : a.toArray()).collect(Collectors.toList());
	}
	
	protected static class Trace {
		
		public final int node, dist;

		public Trace(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
		
	}
	
	protected static class IntArray {
		
		private int[] elements;
		
		private int size, stride;
		
		public IntArray() {
			this(new int[5], 3);
		}

		public IntArray(int[] elements, int stride) {
			this.elements = elements;
			this.stride = stride;
			this.size = 0;
		}
		
		public void add(int elem) {
			this.ensureCapacity(1 + this.size);
			this.elements[this.size++] = elem; 
		}
		
		public int[] toArray() {
			return Arrays.copyOfRange(this.elements, 0, this.size);
		}
		
		protected void ensureCapacity(int target) {
			int len = this.elements.length;
			while(len < target) {
				int temp = len;
				len += this.stride;
				this.stride = temp;
			}
			
			if(this.elements.length < len) {
				int[] elem = Arrays.copyOf(this.elements, len);
				this.elements = elem;
			}
		}
		
	}

}
