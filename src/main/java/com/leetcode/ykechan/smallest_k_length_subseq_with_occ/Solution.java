package com.leetcode.ykechan.smallest_k_length_subseq_with_occ;

import java.util.Arrays;

public class Solution {
	
	public String smallestSubsequence(String s, int k, char letter, int rep) {	
		Sequence seq = this.analyze(s, letter);
		
		int[] next = seq.next;
		int[] occurences = seq.occurences;
		
		System.out.println(Arrays.toString(next));
		System.out.println(Arrays.toString(occurences));
		
		int req = rep;
		
		StringBuilder buf = new StringBuilder();
		int i = 0;
		while(i < s.length()){
			if(buf.length() == k) {
				// done
				break;
			}
			
			char ch = s.charAt(i);			
			int len = buf.length();
			if(occurences[i] < req || s.length() - i < k - len){
				throw new IllegalArgumentException("Unable to construct " 
					+ k + " with " + req + " of " + letter + " at " + i);
			}
			
			int pos = next[i];
			int suffixOcc = pos < 0 ? -1 : occurences[pos];
			int suffixLen = pos < 0 ? -1 : s.length() - pos;
			if(k - len <= req && ch != letter){
				// can't use this
				i++;
				continue;
			}
			
			if(pos >= 0 && suffixOcc >= req && suffixLen >= k - len){
				// use next better char
				i = pos;
				continue;
			}
			
			buf.append(ch);
			req -= ch == letter ? 1 : 0; 
			i++;
		}
		return buf.toString();
	}	
	
	protected Sequence analyze(String s, char letter) {
		int len = s.length();
		
		int[] next = new int[len];
		next[len - 1] = -1;
		
		int[] occurences = new int[len];
		occurences[len - 1] = s.charAt(len - 1) == letter ? 1 : 0;
		
		int[] dist = new int[26];
		Arrays.fill(dist, -1);
		dist[s.charAt(len - 1) - 'a'] = len - 1;
		
		for(int i = len - 2; i >= 0; i--) {
			char ch = s.charAt(i);
			int val = ch - 'a';
			
			int min = -1;
			for(int j = 0; j < val; j++){
				if(min < 0 || (dist[j] >= 0 && min > dist[j])){
					min = dist[j];
				}
			}
			
			next[i] = min;
			occurences[i] = occurences[i + 1] + (ch == letter ? 1 : 0);
			dist[val] = i;
		}
		return new Sequence(s, next, occurences);
	}
	
	protected static class Sequence {
		
		public final String str;
		
		public final int[] next;
		
		public final int[] occurences;

		public Sequence(String str, int[] next, int[] occurences) {
			this.str = str;
			this.next = next;
			this.occurences = occurences;
		}
		
	}

}
