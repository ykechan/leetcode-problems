package com.leetcode.ykechan.substring_with_largest_variance;

import java.util.Arrays;

public class Solution {
	
	public int largestVariance(String s) {
		boolean[] found = new boolean[1 + 'z' - 'a'];
		Arrays.fill(found, false);
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			found[ch - 'a'] = true;
		}
		
		int max = 0;
		for(char m = 'a'; m <= 'z'; m++){
			if(!found[m - 'a']) {
				continue;
			}
			
			for(char n = 'a'; n <= 'z'; n++) {
				if(m == n || !found[n - 'a']) {
					continue;
				}
				
				int[] array = this.trim(s, m, n);
				int var = this.largestVariance(array);
				//System.out.println(Arrays.toString(array) + ": [" + m + "," + n + "] => " + var);
				if(var > max) {
					max = var;
				}
			}
		}
		return max;
	}	
	
	public int largestVariance(int[] array) {
		if(array.length < 2){
			return 0;
		}
		
		int[] prefix = new int[array.length];		
		prefix[0] = array[0];
		for(int i = 1; i < prefix.length; i++) {
			prefix[i] = prefix[i - 1] + array[i];
		}
		
		int min = prefix[0] < 0 ? prefix[0] : 0;
		int max = prefix[1];
		for(int i = 2; i < prefix.length; i++) {
			int var = prefix[i] - min;
			if(var > max) {
				max = var;
			}
			
			if(prefix[i - 1] < min) {
				min = prefix[i - 1];
			}
		}
		return max;
	}
	
	public int[] trim(String str, char m, char n) {
		int len = 0;
		int[] array = new int[str.length()];
		int i = 0;
		
		while(i < str.length() && str.charAt(i) != m && str.charAt(i) != n) {
			i++;
		}
		
		if(i >= str.length()){
			return new int[0];
		}
		
		array[len++] = str.charAt(i++) == m ? 1 : -1;
		while(i < str.length()){
			char ch = str.charAt(i++);
			if(ch != m && ch != n) {
				continue;
			}
			
			int sgn = ch == m ? 1 : -1;
			if(sgn < 0 || sgn != (array[len - 1] < 0 ? -1 : 1)) {
				array[len++] = sgn;
				continue;
			}
			
			array[len - 1] += sgn;
		}
		return Arrays.copyOf(array, len);
	}
	
	public int bruteForce(String str) {
		int max = 0;
		String sub = "";
		for(int i = 0; i < str.length(); i++) {
			for(int j = i + 1; j <= str.length(); j++) {
				int var = this.variance(str.substring(i, j));				
				if(var > max) {
					sub = str.substring(i, j);					
					max = var;					
				}
			}
		}
		System.out.println(sub);		
		return max;
	}
	
	public int variance(String str) {
		int[] freq = new int[1 + 'z' - 'a'];
		Arrays.fill(freq, 0);
		
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			freq[ch - 'a']++;
		}
		
		int max = Arrays.stream(freq).filter(f -> f > 0).max().orElse(0);
		int min = Arrays.stream(freq).filter(f -> f > 0).min().orElse(0);
		return max - min;
	}

}
