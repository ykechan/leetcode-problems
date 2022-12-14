package com.leetcode.ykechan.multiply_strings;

public class Solution {
	
	public String multiply(String num1, String num2) {
		String result = "";
		for(int i = 0; i < num2.length(); i++) {
			int k = num2.charAt(i) - '0';
			if(!"0".equals(result)) {
				result += '0';
			}
			if(k == 0) {
				continue;
			}
			
			String mult = this.multiply(num1, k);
			String next = this.add(result, mult);
			result = next;
		}
		return result;
	}
	
	protected String multiply(String num, int k) {
		StringBuilder buf = new StringBuilder();
		
		int carry = 0;
		for(int i = num.length() - 1; i >= 0; i--){
			char ch = num.charAt(i);
			int digit = ch - '0';
			
			int mult = digit * k + carry;
			buf.append((char) ('0' + (mult % 10)));
			carry = mult / 10;
		}
		if(carry > 0){
			buf.append(carry);
		}
		return buf.reverse().toString();
	}
	
	protected String add(String num1, String num2) {
		if(num1.length() < num2.length()){
			return this.add(num2, num1);
		}
		
		StringBuilder buf = new StringBuilder();
		
		int offset = num1.length() - num2.length();
		int carry = 0;
		for(int i = num1.length() - 1; i >= 0; i--) {
			char ch0 = num1.charAt(i);
			char ch1 = i < offset ? '0' : num2.charAt(i - offset);
			
			int d0 = ch0 - '0';
			int d1 = ch1 - '0';
			
			int digit = d0 + d1 + carry;
			buf.append((char) ('0' + (digit % 10)));
			carry = digit / 10;
		}
		
		if(carry > 0){
			buf.append(carry);
		}
		return buf.reverse().toString();
	}

}
