package com.leetcode.ykechan.string_compression_ii;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
	
	protected static final int RADIX = 1 + ('z' - 'a');
	
	public int getLengthOfOptimalCompression(String s, int k) {
		Map<Tuple, int[]> mem = new TreeMap<>();
		int[] tokens = this.optimalCompression(0, s, k * (1 + s.length()), mem);
		return this.lengthOf(tokens);
	}
	
	protected int[] optimalCompression(int prefix, String s, int hash, Map<Tuple, int[]> mem) {
		Tuple t = new Tuple(prefix, hash);
		if(mem.containsKey(t)){
			return mem.get(t);
		}
		
		int width = 1 + s.length();
		int pos = hash % width;
		int num = hash / width;
		if(pos >= s.length() || num >= s.length() - pos){
			return prefix < 1 ? new int[0] : new int[] {prefix};
		}
		
		int prev = prefix % RADIX;
		int run = prefix / RADIX;
		
		int curr = 1 + (s.charAt(pos) - 'a');
		if(curr == prev){
			// merge with previous run
			int[] suffix0 = this.optimalCompression((run + 1) * RADIX + prev, s, hash + 1, mem);
			// remove this character
			int[] suffix1 = num < 1 ? suffix0 : this.optimalCompression(prefix, s, hash - width + 1, mem);
			
			int[] tokens = suffix0 == suffix1 || this.lengthOf(suffix0) < this.lengthOf(suffix1)
				? suffix0 : suffix1;
			mem.put(t, tokens);
			return tokens;
		}
		
		// keep this character
		
		int[] suffix0 = this.join(prefix, this.optimalCompression(RADIX + curr, s, hash + 1, mem));
		// remove this character
		int[] suffix1 = num < 1 ? suffix0 : this.optimalCompression(prefix, s, hash - width + 1, mem);
		int[] tokens = suffix0 == suffix1 || this.lengthOf(suffix0) < this.lengthOf(suffix1)
				? suffix0 : suffix1;
		mem.put(t, tokens);	
		return tokens;
	}
	
	protected int[] join(int prefix, int[] suffix) {
		if(suffix.length < 1){
			return prefix < 1 ? new int[0] : new int[] {prefix};
		}
		
		if(prefix < 1) {
			return Arrays.copyOf(suffix, suffix.length);
		}
		
		int prev = prefix % RADIX;
		int run = prefix / RADIX;
		
		int curr = suffix[0] % RADIX;
		int len = suffix[0] / RADIX;
		
		if(prev != curr){
			int[] tokens = new int[1 + suffix.length];
			tokens[0] = prefix;
			System.arraycopy(suffix, 0, tokens, 1, suffix.length);
			return tokens;
		}
		
		int[] tokens = Arrays.copyOf(suffix, suffix.length);
		tokens[0] = (len + run) * RADIX + curr;
		return tokens;
	}
	
	protected int lengthOf(int[] tokens) {
		int len = 0;
		for(int token : tokens) {
			int run = token / RADIX;
			if(run < 2){
				len++;
				continue;
			}
			
			if(run < 10){
				len += 2;
				continue;
			}
			
			if(run < 100){
				len += 3;
				continue;
			}
			
			len += 1 + String.valueOf(run).length();
		}
		return len;
	}
	
	protected String encode(int[] tokens) {
		StringBuilder buf = new StringBuilder();
		for(int token : tokens) {
			char ch = (char) ('a' + (token % RADIX) - 1);
			int run = token / RADIX;
			buf.append(ch);
			if(run > 1){
				buf.append(Integer.toString(run));
			}
		}
		return buf.toString();
	}
	
	protected static class Tuple implements Comparable<Tuple> {
		
		public final int lower, upper;

		public Tuple(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}

		@Override
		public int compareTo(Tuple t) {
			int cmp = this.upper < t.upper ? -1 : this.upper > t.upper ? 1 : 0;
			if(cmp == 0){
				cmp = this.lower < t.lower ? -1 : this.lower > t.lower ? 1 : 0;
			}
			return cmp;
		}
		
		
		
	}

}
