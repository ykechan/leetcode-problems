package com.leetcode.ykechan.expression_add_operators;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
	
	public List<String> addOperators(String num, int target){
		Deque<Expr> stack = new ArrayDeque<>();
		for(int j = 0; j < num.length(); j++) {			
			long val = Long.valueOf(num.substring(0, j + 1));
			if(num.charAt(0) == '0' && j > 0) {
				continue;
			}
			stack.push(new Expr(j + 1, 0, val, String.valueOf(val)));
		}
		
		Set<String> results = new TreeSet<>();
		while(!stack.isEmpty()){
			Expr expr = stack.pop();
			System.out.println(expr.str + " = " + expr.prefix + " + " + expr.suffix + " => " + (expr.prefix + expr.suffix));
			if(expr.prefix + expr.suffix == target && expr.index >= num.length()){
				results.add(expr.str);
				continue;
			}
			
			int i = expr.index;
			{
				long prefix = expr.prefix + expr.suffix;
				for(int j = i; j < num.length(); j++) {
					int val = Integer.valueOf(num.substring(i, j + 1));
					if(num.charAt(i) == '0' && j + 1 - i > 1){
						continue;
					}
					// add
					stack.push(new Expr(j + 1, prefix,  val, 
						expr.str + "+" + String.valueOf(val)));
					
					// minus
					stack.push(new Expr(j + 1, prefix, -val, 
						expr.str + "-" + String.valueOf(val)));
					
					// mult
					stack.push(new Expr(j + 1, expr.prefix, expr.suffix * val, 
							expr.str + "*" + String.valueOf(val)));
				}
			}
		}
		return Arrays.asList(results.toArray(new String[0]));
	}
	
	public static class Expr {
		
		public final int index;
		
		public final long prefix, suffix;
		
		public final String str;

		public Expr(int index, long prefix, long suffix, String str) {
			this.index = index;
			this.prefix = prefix;
			this.suffix = suffix;
			this.str = str;
		}
		
	}
	
}
