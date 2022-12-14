package com.leetcode.ykechan.repeated_dna_sequences;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Solution {
	
	public static final int BASE = 4 * 4 * 4 * 4 * 4 * 4 * 4 * 4 * 4 * 4;
	
	public List<String> findRepeatedDnaSequences(String s){
		int hash = 0;
		if(s.length() < 10){
			return Collections.emptyList();
		}
		
		Set<Integer> set = new TreeSet<>();
		Set<Integer> found = new TreeSet<>();
		for(int i = 0; i < s.length(); i++){
			char ch = s.charAt(i);
			hash = (4 * hash + this.toInt(ch)) % BASE;
			if(i > 8){
				if(set.contains(hash)) {
					found.add(hash);
				}else {
					set.add(hash);
				}
			}
		}
		return found.stream().map(this::toStr).collect(Collectors.toList());
	}
	
	public int toInt(char ch) {
		switch(ch) {
			case 'A':
				return 0;
				
			case 'C':
				return 1;
				
			case 'G':
				return 2;
				
			case 'T':
				return 3;
				
			default:
				break;
		}
		
		throw new IllegalArgumentException("Invalid character " + ch);
	}
	
	public String toStr(int hash) {
		StringBuilder buf = new StringBuilder();
		int temp = hash;
		for(int i = 0; i < 10; i++) {
			switch(temp % 4) {
				case 0:
					buf.append('A');
					break;
					
				case 1:
					buf.append('C');
					break;
					
				case 2:
					buf.append('G');
					break;
					
				case 3:
					buf.append('T');
					break;
			
				default:
					throw new IllegalArgumentException("Invalid hash " + hash);
			}
			temp /= 4;
		}
		return buf.reverse().toString();
	}

}
