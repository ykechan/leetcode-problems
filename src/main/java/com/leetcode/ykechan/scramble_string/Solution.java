package com.leetcode.ykechan.scramble_string;

import java.util.Arrays;
import java.util.List;

public class Solution {
	
	public static final char NULL = '\0';
	
	public static final char TRUE = 't';
	
	public static final char FALSE = 'f';
	
	public boolean isScramble(String s1, String s2) {
		if(s1.length() != s2.length()){
			return false;
		}
		
		if(s1.equals(s2)){
			return true;
		}
		
		int len = s1.length();
		char[][] mem = new char[1 + len][];
		for(int i = 3; i <= len; i++){
			mem[i] = new char[len * len];
			Arrays.fill(mem[i], NULL);
		}
		return this.isScramble(s1, 0, s2, 0, len, Arrays.asList(mem));
	}
	
	public boolean isScramble(String s, int src, String t, int dest, int len, List<char[]> mem) {
		switch(len){
			case 0 :
				return true;
				
			case 1 :
				return s.charAt(src) == t.charAt(dest);
				
			case 2 :
				return (s.charAt(src) == t.charAt(dest) && s.charAt(src + 1) == t.charAt(dest + 1))
					|| (s.charAt(src) == t.charAt(dest + 1) && s.charAt(src + 1) == t.charAt(dest));
				
			default:				
				break;
		}
		
		int index = src * s.length() + dest;
		char[] dynProg = mem.get(len);
		switch(dynProg[index]) {
			case NULL:
				break;
				
			case TRUE:
				return true;
				
			case FALSE:
				return false;
				
			default:
				throw new IllegalStateException();
		}
		
		int[] delta = new int[1 + 'z' - 'a'];
		for(int i = 0; i < len; i++){
			char a = s.charAt(src + i);
			char b = t.charAt(dest + i);
			
			delta[a - 'a']++;
			delta[b - 'a']--;
		}
		
		for(int i = 0; i < delta.length; i++){
			if(delta[i] != 0){
				dynProg[index] = FALSE;
				return false;
			}
		}
		
		boolean equals = true;
		for(int i = 0; i < len; i++){
			if(s.charAt(src + i) != t.charAt(dest + i)){
				equals = false;
				break;
			}
		}
		
		if(equals){
			dynProg[index] = TRUE;
			return true;
		}
		
		for(int i = 1; i < len; i++){
			{
				// no swapping
				// s[src..src + i] <=> t[dest..dest+i] && s[src+i..src+len] <=> t[dest+i..dest+len]
				boolean prefix = this.isScramble(s, src, t, dest, i, mem);
				System.out.println(s.substring(src, src + i) + " :: " + t.substring(dest, dest + i) + " => " + prefix);
				if(prefix){
					boolean suffix = this.isScramble(s, src + i, t, dest + i, len - i, mem);
					System.out.println(s.substring(src + i, src + len) + " :: " + t.substring(dest + i, dest + len) + " => " + suffix);
					if(suffix){
						dynProg[index] = TRUE;
						return true;
					}
				}
			}
			
			{
				// swapping
				// s[src..src+i] <=> t[dest+len-i..dest+len]
				boolean prefix = this.isScramble(s, src, t, dest + len - i, i, mem);
				System.out.println(s.substring(src, src + i) + " :: " + t.substring(dest + len - i, dest + len) + " => " + prefix);
				if(prefix){
					boolean suffix = this.isScramble(s, src + i, t, dest, len - i, mem);
					System.out.println(s.substring(src + i, src + len - i) + " :: " + t.substring(dest, dest + len - i) + " => " + suffix);
					if(suffix){
						dynProg[index] = TRUE;
						return true;
					}
				}
			}
		}
		dynProg[index] = FALSE;
		return false;
	}

}
