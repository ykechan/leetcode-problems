package com.leetcode.ykechan.delete_and_earn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	
	public int deleteAndEarn(int[] nums) {
		Arrays.sort(nums);
		
		List<int[]> run = new ArrayList<>();
		
		int count = 1;
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] == nums[i - 1]) {
				count++;
				continue;
			}
			
			run.add(new int[] {nums[i - 1], count});
			count = 1;
		}
		
		if(count > 0) {
			run.add(new int[] {nums[nums.length - 1], count});
		}
        return 0;
    }

}
