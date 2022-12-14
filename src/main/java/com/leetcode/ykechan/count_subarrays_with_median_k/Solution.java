package com.leetcode.ykechan.count_subarrays_with_median_k;

import java.util.Arrays;

public class Solution {
	
	public int countSubarrays(int[] nums, int k) {
		int pos = -1;
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == k){
				pos = i;
				break;
			}
		}
		
		if(pos < 0){
			return 0;
		}
		
		int count = 0;
		int[] lower = new int[nums.length];
		int[] upper = new int[nums.length];
		
		//System.out.println("pos=" + pos);
		
		int delta = 0;
		for(int i = 0; i < pos; i++) {
			delta += nums[i] < k ? -1 : nums[i] > k ? 1 : 0;
			//System.out.println("#" + i + ": " + delta);
			if(delta < 0) {
				lower[-delta]++;
			}else {
				upper[delta]++;
			}
		}
		
		//System.out.println(Arrays.toString(lower));
		//System.out.println(Arrays.toString(upper));
		
		for(int i = pos; i < nums.length; i++) {
			delta += nums[i] < k ? -1 : nums[i] > k ? 1 : 0;			
			if(delta == 0 || delta == 1) {
				count++;
			}
			
			if(delta < 0) {
				int target = -delta;
				count += lower[target] + (target + 1 >= lower.length ? 0 : lower[target + 1]);
			}else if(delta > 0) {
				int target = delta;
				count += upper[target] + (target == 0 ? 0 : upper[target - 1]);
			}else {
				count += upper[0] + (lower.length > 1 ? lower[1] : 0);
			}
			
			//System.out.println("#" + i + ": " + delta + " => " + count);
		}
		return count;
	}
	
	public int bruteForce(int[] nums, int k) {
		int ans = 0;
		for(int j = 0; j < nums.length; j++) {
			for(int i = 0; i <= j; i++) {
				int m = this.median(nums, i, j + 1);
				System.out.println(Arrays.toString(Arrays.copyOfRange(nums, i, j + 1)) + " => " + m);
				if(m == k) {					
					ans++;
				}
			}
		}
		return ans;
	}
	
	protected int median(int[] array, int begin, int end) {
		int[] temp = Arrays.copyOfRange(array, begin, end);
		Arrays.sort(temp);
		int i =  (temp.length / 2) - (temp.length % 2 == 0 ? 1 : 0);
		return temp[i];
	}

}
