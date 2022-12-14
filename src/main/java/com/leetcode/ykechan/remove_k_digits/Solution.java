package com.leetcode.ykechan.remove_k_digits;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
	
	public String removeKdigits(String num, int k) {
		int n = num.length() - k;
		if(n < 1){
			return "0";
		}
		
        return this.pickNDigits(num, n);
    }
	
	protected String pickNDigits(String num, int n) {
		StringBuilder buf = new StringBuilder();
		int[] next = this.nextMin(num, 0);		
		System.out.println(Arrays.toString(next));
		for(int i = 0; i < num.length(); i++) {
			if(buf.length() >= n) {
				break;
			}
			
			if(next[i] < 0 || num.length() - next[i] <= n - buf.length()) {
				char ch = num.charAt(i);
				buf.append(ch);
			}
		}
		
		while(buf.length() > 1 && buf.charAt(0) == '0') {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}
	
	protected int[] nextMin(String num, int begin) {
		Deque<Integer> stack = new ArrayDeque<>();
		
		int[] next = new int[num.length()];
		
		for(int i = begin; i < num.length(); i++) {
			char ch = num.charAt(i);
			while(!stack.isEmpty()) {
				int top = stack.peek();
				if(num.charAt(top) <= ch) {
					break;
				}
				
				next[top] = i;
				stack.pop();
			}
			
			stack.push(i);
		}
		
		while(!stack.isEmpty()) {
			int top = stack.pop();
			next[top] = -1;
		}
		return next;
	}

}
