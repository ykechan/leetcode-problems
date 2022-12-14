package com.leetcode.ykechan.closest_room;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
	
	public int[] closestRoom(int[][] rooms, int[][] queries) {
		int len = queries.length;
		Query[] temp = new Query[len];
		for(int i = 0; i < temp.length; i++) {
			int[] query = queries[i];
			temp[i] = new Query(i, query[0], query[1]);
		}
		
		Arrays.sort(temp, Comparator.comparingInt(q -> q.size));
		
		IntArray[] buckets = new IntArray[len];
		for(int[] room : rooms) {
			int j = this.search(temp, room[1]);
			if(j < 0){
				continue;
			}
			
			IntArray array = buckets[j];
			if(array == null){
				array = new IntArray();
				buckets[j] = array;
			}
			array.add(room[0]);
		}
		
		int[] result = new int[len];
		
		AvlNode root = null;
		for(int i = len - 1; i >= 0; i--) {
			int[] items = buckets[i] == null ? new int[0] : buckets[i].toArray();
			for(int j : items) {
				root = this.insert(root, j);
			}
			
			Query q = temp[i];
			result[q.index] = this.closest(root, q.pos);
		}
		return result;
	}
	
	protected int search(Query[] array, int target) {
		if(array[0].size > target){
			return -1;
		}
		
		int upper = array.length - 1;
		if(array[upper].size <= target){
			return upper;
		}
		
		int lower = 0;
		while(upper - lower > 1) {			
			int mid = (upper + lower) / 2;
			int val = array[mid].size;
			
			if(val > target) {
				upper = mid;
			} else {
				lower = mid;
			}
		}
		return lower;
	}
	
	public static class Query {
		
		public final int index, pos, size;

		public Query(int index, int pos, int size) {
			this.index = index;
			this.pos = pos;
			this.size = size;
		}
		
	}
	
	protected int closest(AvlNode node, int val) {
		if(node == null){
			return -1;
		}
		
		AvlNode curr = node;
		int min = curr.val;
		
		while(curr != null){
			int cmp = Math.abs(val - curr.val) - Math.abs(val - min);
			if(min < 0 || cmp < 0 || (cmp == 0 && curr.val < min)){
				min = curr.val;
			}
			
			if(val < curr.val){
				curr = curr.left;
			} else if(val > curr.val) {
				curr = curr.right;
			} else {
				return curr.val;
			}
			
		}
		return min;
	}
	
	protected AvlNode insert(AvlNode node, int val) {
		if(node == null) {
			return new AvlNode(val);
		}
		
		if(val < node.val) {
			node.left = this.insert(node.left, val);
			return this.rotate(node);
		}
		
		if(val > node.val) {
			node.right = this.insert(node.right, val);
			return this.rotate(node);
		}
		
		node.weight++;
		return node;
	}	
	
	protected AvlNode rotate(AvlNode node) {
		if(node == null) {
			throw new IllegalArgumentException("No node to rotate");
		}
		
		int left = this.heightOf(node.left);
		int right = this.heightOf(node.right);
		
		if(left - right > 1) {
			AvlNode lnode = node.left;
			if(this.heightOf(lnode.left) < this.heightOf(lnode.right)) {
				node.left = this.rotateLeft(lnode);
				return this.rotate(this.maintain(node));
			}
			return this.rotateRight(node);
		}
		
		if(right - left > 1) {
			AvlNode rnode = node.right;
			if(this.heightOf(rnode.left) > this.heightOf(rnode.right)) {
				node.right = this.rotateRight(rnode);
				return this.rotate(this.maintain(node));
			}
			return this.rotateLeft(node);
		}
		
		return this.maintain(node);
	}
	
	protected AvlNode rotateLeft(AvlNode node) {
		//
		//       A               C
		//      / \             / \
		//     B   C    =>     A   E
		//        / \         / \
		//       D   E       B   D
		//
		AvlNode right = node.right;
		if(right == null) {
			throw new UnsupportedOperationException("Illegal to rotate left when no right child");
		}
		
		node.right = right.left;
		right.left = node;
		
		this.maintain(node);
		
		return this.maintain(right);
	}
	
	protected AvlNode rotateRight(AvlNode node) {
		//
		//        A             B
		//       / \           / \
		//      B   C   =>    D   A
		//     / \               / \
		//    D   E             E   C
		//
		AvlNode left = node.left;
		if(left == null) {
			throw new UnsupportedOperationException("Illegal to rotate right when no left child");
		}
		
		node.left = left.right;
		left.right = node;
		
		this.maintain(node);
		return this.maintain(left);
	}
	
	protected AvlNode maintain(AvlNode node) {
		node.height = 1 + Math.max(this.heightOf(node.left), this.heightOf(node.right));
		return node;
	}
	
	protected int heightOf(AvlNode node) {
		return node == null ? 0 : node.height;
	}
	
	public static class AvlNode {
		
		public int val;
		
		public int weight;
		
		public int height;
		
		public AvlNode left, right;

		public AvlNode(int val) {
			this.val = val;
			this.weight = 1;
			this.height = 1;
		}
		
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
