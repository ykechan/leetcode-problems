package com.leetcode.ykechan.subarray_with_elem_gt_varying_thres;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
	
	public int validSubarraySize(int[] nums, int threshold) {
		int len = nums.length;
		int[] weights = new int[len];
		for(int i = 0; i < len; i++) {
			if(threshold < nums[i]) {
				return 1;
			}
			weights[i] = (threshold / nums[i]) + 1;
			System.out.println("#" + i + ": " + weights[i]);
		}
		
		int[] backward = new int[len];
		Deque<Integer> stack = new ArrayDeque<>();
		for(int i = 0; i < len; i++) {
			int w = weights[i];
			while(!stack.isEmpty()) {
				int j = stack.peek();
				if(weights[j] > w) {
					break;
				}
				
				stack.pop();
			}
			
			backward[i] = stack.isEmpty() ? 0 : 1 + stack.peek();
			stack.push(i);
		}
		
		stack = new ArrayDeque<>();
		int[] forward = new int[len];
		for(int i = len - 1; i >= 0; i--) {
			int w = weights[i];
			while(!stack.isEmpty()) {
				int j = stack.peek();
				if(weights[j] > w) {
					break;
				}
				
				stack.pop();
			}
			forward[i] = stack.isEmpty() ? len : stack.peek();
			stack.push(i);
		}
		
		//System.out.println(Arrays.toString(backward));
		//System.out.println(Arrays.toString(forward));
		for(int i = 0; i < len; i++) {			
			int span = forward[i] - backward[i];
			System.out.println("#" + i + ": [" + backward[i] + "," + forward[i] + ") = " + span);
			if(span >= weights[i]){
				return weights[i];
			}
		}
		return -1;
	}
	
	public int bruteForce(int[] nums, int thres) {
		for(int i = 0; i < nums.length; i++) {
			for(int j = i + 1; j < nums.length; j++) {
				int len = j - i;
				boolean valid = true;
				for(int k = i; k < j; k++) {
					if(len * nums[k] <= thres) {
						valid = false;
						break;
					}
				}
				
				if(valid) {
					System.out.println("[" + i + ", " + j + "): " 
							+ Arrays.toString(Arrays.copyOfRange(nums, i, j)));
					return len;
				}
			}
		}
		return -1;
	}
	

}
