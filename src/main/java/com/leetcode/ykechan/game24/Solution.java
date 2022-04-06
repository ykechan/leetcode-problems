package com.leetcode.ykechan.game24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	
	public boolean judgePoint24(int[] cards) {
		String items = IntStream.range(0, cards.length)
			.mapToObj(String::valueOf).collect(Collectors.joining());
		String tokens = Arrays.stream(cards)
			.mapToObj(String::valueOf).collect(Collectors.joining());
		
		List<Node> forest = this.generateTrees(items);
		Set<String> perm = this.permute(tokens);
		
		Rational target = new Rational(24);
		
		for(String p : perm) {
			//System.out.println(p);
			for(Node tree : forest) {
				//this.print(System.out, tree);
				Set<Rational> ans = this.evalAll(tree, p);
				//System.out.println(ans);
				
				if(ans.contains(target)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	protected Set<String> permute(String tokens) {
		if(tokens.isEmpty()) {
			return Collections.emptySet();
		}
		
		if(tokens.length() < 2) {
			return Collections.singleton(tokens);
		}
		
		Set<String> result = new TreeSet<>();
		for(int i = 0; i < tokens.length(); i++) {
			char ch = tokens.charAt(i);
			String other = tokens.substring(0, i) + tokens.substring(i + 1);
			
			Set<String> set = this.permute(other);
			for(String suffix : set) {
				result.add(ch + suffix);
			}
		}
		return result;
	}
	
	protected Set<Rational> evalAll(Node tree, String tokens) {
		if(tree.token >= '0' && tree.token <= '9') {
			int index = tree.token - '0';
			return Collections.singleton(new Rational(tokens.charAt(index) - '0', 1));
		}
		
		Set<Rational> left = this.evalAll(tree.left, tokens);
		Set<Rational> right = this.evalAll(tree.right, tokens);
		
		Set<Rational> ans = new TreeSet<>();
		
		for(Rational a : left) {
			for(Rational b : right) {
				ans.add(a.add(b));
				ans.add(a.sub(b));
				ans.add(a.mul(b));
				if(!b.isZero()) {
					ans.add(a.div(b));
				}
			}
		}
		return ans;
	}
	
	protected List<Node> generateTrees(String items) {
		if(items.isEmpty()) {
			return Collections.emptyList();
		}
		
		if(items.length() < 2) {
			return Collections.singletonList(new Node(items.charAt(0)));
		}
		
		List<Node> forest = new ArrayList<>();
		for(int i = 1; i < items.length(); i++) {
			String left = items.substring(0, i);
			String right = items.substring(i);
			
			List<Node> ltree = this.generateTrees(left);
			List<Node> rtree = this.generateTrees(right);
			
			for(Node a : ltree) {
				for(Node b : rtree) {
					forest.add(new Node('?', a, b));
				}
			}
		}
		return forest;
	}
	
	
	public static class Node {
		
		public char token;
		
		public Node left, right;
		
		public Node(char token) {
			this(token, null, null);
		}

		public Node(char token, Node left, Node right) {
			this.token = token;
			this.left = left;
			this.right = right;
		}
		
	}
	
	public static class Rational implements Comparable<Rational> {
		
		public final int p, q;
		
		public Rational(int i) {
			this(i, 1);
		}

		public Rational(int p, int q) {
			this.p = p;
			this.q = q;
		}
		
		public boolean isZero() {
			return this.p == 0;
		}
		
		public int sgn() {
			if(this.p == 0 || this.q == 0){
				return 0;
			}
			
			return (this.p > 0 ? 1 : -1) * (this.q > 0 ? 1 : -1);
		}
		
		public Rational add(Rational g) {
			Rational f = this;			
			return new Rational(f.p * g.q + g.p * f.q, f.q * g.q);
		}
		
		public Rational sub(Rational g) {
			Rational f = this;			
			return new Rational(f.p * g.q - g.p * f.q, f.q * g.q);
		}
		
		public Rational mul(Rational g) {
			Rational f = this;			
			return new Rational(f.p * g.p, f.q * g.q);
		}
		
		public Rational div(Rational g) {
			Rational f = this;			
			return new Rational(f.p * g.q, f.q * g.p);
		}

		@Override
		public int compareTo(Rational g) {
			Rational f = this;
			
			int sgnF = f.sgn();
			int sgnG = g.sgn();
			
			if(sgnF != sgnG) {
				return sgnF < sgnG ? -1 : 1;
			}
			
			int sgn = (f.q < 0 ? -1 : 1) * (g.q < 0 ? -1 : 1);
			
			int a = f.p * g.q;
			int b = g.p * f.q;
			
			return sgn * (a < b ? -1 : a > b ? 1 : 0);
		}
		
		@Override
		public boolean equals(Object obj) {
			if(obj instanceof Rational){
				Rational f = this;
				Rational g = (Rational) obj;
				
				return f.p * g.q == g.p * f.q;
			}
			return false;
		}

		@Override
		public String toString() {
			if(this.q == 1) {
				return String.valueOf(this.p);
			}
			
			if(this.q == -1) {
				return String.valueOf(-this.p);
			}
			
			return this.p + "/" + this.q;
		}
		
	}

}
