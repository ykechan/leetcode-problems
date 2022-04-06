package com.leetcode.ykechan.util;

public class IntervalTree {
	
	public Node insert(Node node, int begin, int end) {
		if(end < begin) {
			throw new IllegalArgumentException("Not a open interval (" + begin + "," + end + ")");
		}
		
		if(begin == end) {
			return node;
		}
		
		if(node == null){
			return new Node(begin, end);
		}
		
		if(end <= node.begin) {
			return node.replaceLeft(this.insert(node.left, begin, end));
		}
		
		if(begin >= node.end) {
			return node.replaceRight(this.insert(node.right, begin, end));
		}
		
		if(begin <= node.begin && end >= node.end) {
			
		}
		return null;
	}
	
	public static class Node {
		
		public final int begin, end;
		
		public final Node left, right;
		
		public Node(int begin, int end) {
			this(begin, end, null, null);
		}

		public Node(int begin, int end, Node left, Node right) {
			this.begin = begin;
			this.end = end;
			this.left = left;
			this.right = right;
		}
		
		public Node replaceLeft(Node left) {
			return this.replace(left, this.right);
		}
		
		public Node replaceRight(Node right) {
			return this.replace(this.left, right);
		}
		
		public Node replace(Node left, Node right) {	
			return new Node(this.begin, this.end, left, right);
		}
		
	}

}
