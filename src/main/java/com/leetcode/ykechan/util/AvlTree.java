/*
 * Copyright (c) 2022 Y.K. Chan
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE. 
 */
package com.leetcode.ykechan.util;

import java.util.Map;

public class AvlTree {
	
	public void insert(int val) {
		this.root = this.insert(this.root, val);
	}
	
	public void remove(int val) {
		this.root = this.remove(this.root, val);
	}
	
	public int find(int val) {
		AvlNode curr = this.root;
		while(curr != null){
			if(curr.val < val) {
				curr = curr.right;
			}else if(curr.val > val) {
				curr = curr.left;
			}else {
				return curr.weight;
			}
		}
		return 0;
	}
	
	public boolean equals(Map<Integer, Integer> map) {
		return false;
	}
	
	private AvlNode root;
	
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
	
	protected AvlNode remove(AvlNode node, int val) {
		if(node == null) {
			return node;
		}
		
		if(val < node.val) {
			node.left = this.remove(node.left, val);
			return this.rotate(node);
		}
		
		if(val > node.val) {
			node.right = this.remove(node.right, val);
			return this.rotate(node);
		}
		
		if(--node.weight > 0) {
			return node;
		}
		
		if(node.left == null || node.right == null) {
			return node.left != null ? node.left : node.right;
		}
		
		AvlNode target = node.left;
		while(target.right != null){
			target = target.right;
		}
		
		node.val = target.val;
		node.weight = target.weight;
		node.left = this.removeSuccessor(node.left);
		return this.rotate(node);
	}
	
	protected AvlNode removeSuccessor(AvlNode node) {
		if(node.right != null) {
			node.right = this.removeSuccessor(node.right);
			return this.rotate(node);
		}
		
		return node.left;
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

}
