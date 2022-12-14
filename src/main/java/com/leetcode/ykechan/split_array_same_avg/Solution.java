package com.leetcode.ykechan.split_array_same_avg;

import java.util.Arrays;

public class Solution {	
	
	public static final byte TRUE = 1;
	
	public static final byte FALSE = -1;
	
	public static final byte NULL = 0;
	
	public boolean splitArraySameAverage(int[] nums) {
		int len = nums.length;
		int sum = Arrays.stream(nums).sum();
		if(sum < 1) {
			return len > 1;
		}
		
		byte[] mem = new byte[sum * len * len];
		Arrays.fill(mem, NULL);
		
		for(int i = 1; i < nums.length; i++) {
			if((sum * i) % len != 0) {
				continue;
			}
			
			int target = (sum * i) / len;
			if(this.attain(nums, 0, i, target, mem)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean attain(int[] nums, int begin, int len, int target, byte[] mem) {
		int index = target * nums.length * nums.length + begin * nums.length + len;
		if(mem[index] != NULL) {
			return mem[index] == TRUE;
		}
		
		if(len < 1){
			boolean result = target == 0;
			mem[index] = result ? TRUE : FALSE;
			return result;
		}
		
		if(nums.length - begin < 1){
			return false;
		}
		
		int num = nums[begin];
		boolean result = this.attain(nums, begin + 1, len, target, mem)
			|| (num <= target && this.attain(nums, begin + 1, len - 1, target - num, mem));
		mem[index] = result ? TRUE : FALSE;
		return result;
	}
	
	public boolean bruteForce(int[] nums) {
		int max = 1 << nums.length - 1;
		for(int i = 1; i < max; i++) {
			int left = 0;
			int right = 0;
			
			int numL = 0;
			int numR = 0;
			
			int temp = i;
			for(int j = 0; j < nums.length; j++){
				switch(temp % 2){
					case 0 :
						left += nums[j];
						numL++;
						break;
						
					case 1 :
						right += nums[j];
						numR++;
						break;
				
					default:
						break;
				}
				temp /= 2;
			}
			
			if(left * numR == right * numL) {
				return true;
			}
		}
		return false;
	}
	
}
