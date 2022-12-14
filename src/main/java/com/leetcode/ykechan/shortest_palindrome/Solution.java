package com.leetcode.ykechan.shortest_palindrome;

public class Solution {
	
	public String shortestPalindrome(String s) {
		if(s.length() < 2){
			return s;
		}
		
		int[] jump = this.suffixPrefix(s);
		int len = this.matchReverse(s, s.length(), jump);
		
		if(len < s.length()) {
			String suffix = s.substring(len);
			return new StringBuilder()
				.append(suffix).reverse().append(s).toString();
		}
		return s;
	}
	
	protected int matchReverse(String s, int end, int[] jump) {
		int i = -1;
		for(int j = end - 1; j >= 0; j--) {
			char ch = s.charAt(j);
			if(s.charAt(i + 1) == ch){
				i++;
				continue;
			}
			
			int k = i < 0 ? -1 : jump[i];
			if(i >= 0 && k > 0 && ch == s.charAt(k)){
				i = k;
			} else {
				i = -1;
			}
		}
		//System.out.println("i = " + i);
		return i + 1;
	}
	
	protected int[] suffixPrefix(String s) {
		int[] jump = new int[s.length()];
		int len = 0;
		jump[0] = len;
		
		for(int i = 1; i < s.length(); i++) {			
			char ch = s.charAt(i);
			//System.out.println("#" + i + ": " + ch + ", " + len + " => " + s.charAt(len));
			if(ch == s.charAt(len)){
				jump[i] = ++len;
				continue;
			}
			
			int q = i;
			for(int j = len; j >= 0; j--) {
				if(s.charAt(j) == s.charAt(q)){
					q--;
				}else {
					q = i;
				}
			}
			
			len = i - q;
			jump[i] = len;
		}
		return jump;
	}
	
}
