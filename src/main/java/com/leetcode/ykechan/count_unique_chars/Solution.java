package com.leetcode.ykechan.count_unique_chars;

import java.util.Arrays;

public class Solution {
	
	public int uniqueLetterString(String s){
		int[] prev = new int[26];
		int[] curr = new int[26];
		
		Arrays.fill(prev, -1);
		Arrays.fill(curr, -1);
		
		int count = 0;
		
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			int index = ch - 'A';
			
			prev[index] = curr[index];
			curr[index] = i;
			
			// consider substr [0..i, i + 1)
			//System.out.println("Consider " + s.substring(0, i + 1));
			for(int j = 0; j < curr.length; j++){				
				int at = curr[j];
				if(at < 0) {
					// alpha[j] not found					
					continue;
				}
				
				//System.out.print("@" + ((char) ('A' + j)) + ": ");
				int from = prev[j];
				if(from < 0){
					// substr [0..at, i + 1) contains alpha[j]
					count += at + 1;
					//System.out.println("[" + 0 + ".." + at + ", " + (i + 1) + ") => " + (at + 1));
					continue;
				}
				
				// substr [from + 1..at + 1, i + 1) contains alpha[j] uniquely
				//System.out.println("[" + (from + 1) + ".." + at + ", " + (i + 1) + ") => " + (at - from));
				count += at - from;
			}
		}
		return count;
	}
	
	public int bruteForce(String s) {
		int ans = 0;
		for(int i = 0; i < s.length(); i++) {
			for(int j = i + 1; j <= s.length(); j++) {
				String t = s.substring(i, j);
				ans += this.countUniqueChars(t);
			}
		}
		return ans;
	}
	
	public int countUniqueChars(String t) {
		int[] freq = new int[26];
		for(int i = 0; i < t.length(); i++) {
			char ch = t.charAt(i);
			int idx = ch - 'A';
			freq[idx]++;
		}
		
		return (int) Arrays.stream(freq).filter(f -> f == 1).count();
	}

}
