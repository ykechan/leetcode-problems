package com.leetcode.ykechan.longest_substr_without_repeating_chars;

import java.util.Arrays;

public class Solution {
	
	public int lengthOfLongestSubstring(String s) {
		int[] found = new int[256];
		Arrays.fill(found, -1);
		
		int begin = 0;
		int max = 0;
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			int idx = ch - '\0';
			if(found[idx] >= 0) {
				begin = Math.max(begin, found[idx] + 1);
			}
			
			if(i + 1 - begin > max) {
				max = i + 1 - begin;
			}
			found[idx] = i;
		}
		return max;
	}
	
	public int bruteForce(String s) {
		int max = 0;
		for(int i = 0; i < s.length(); i++) {
			for(int j = i + 1; j <= s.length(); j++) {
				boolean[] contains = new boolean[256];
				boolean valid = true;
				for(int k = i; k < j; k++) {
					char ch = s.charAt(k);
					int idx = ch - '\0';
					if(contains[idx]){
						valid = false;
						break;
					}
					
					contains[idx] = true;
				}
				
				if(valid && j - i > max) {
					max = j - i;
				}
			}
		}
		return max;
	}

}
