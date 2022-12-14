package com.leetcode.ykechan.max_sum_of_3_non_overlapping_subarrays;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
	
	public int[] bruteForce(int[] nums, int k) {
		int[] win = this.slidingWindow(nums, k);
		int max = 0;
		int[] ans = {};
		for(int u = k - 1; u < win.length; u++){
			for(int p = u + k; p < win.length; p++) {
				for(int q = p + k; q < win.length; q++) {
					int s = win[u] + win[p] + win[q];
					if(s > max){
						max = s;
						ans = new int[] {u, p, q};
					}
				}
			}
		}
		return Arrays.stream(ans).map(i -> i - k + 1).toArray();
	}
	
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		int len = nums.length;
		
		int[] win = this.slidingWindow(nums, k);
		//System.out.println(Arrays.toString(win));
		
		int[] stack1 = this.monotonicStack(win, k);
		//System.out.println(Arrays.toString(stack1));
		
		int[] stack2 = this.monotonicStack2(win, stack1, k);
		//System.out.println(Arrays.stream(stack2).mapToObj(i -> "[" + (i / len) + "," + (i % len) + "]").collect(Collectors.joining(",")));
		
		int p = k - 1;
		int q = stack2[p + k];
		int max = win[p] + win[q / len] + win[q % len];
		//System.out.println("(" + p + "," + (q / len) + "," + (q % len) + ") => " + max);
		
		for(int i = k; i < len - k; i++){			
			int j = stack2[i + k];
			if(j < 0){
				continue;
			}
			
			int s = win[i] + win[j / len] + win[j % len];
			//System.out.println("(" + i + "," + (j / len) + "," + (j % len) + ") => " + s);
			if(s > max){
				p = i;
				q = j;
				max = s;
			}
		}
		
		//System.out.println("max = " + max);
		return new int[] {p - (k - 1), (q / len) - (k - 1), (q % len) - (k - 1)};
	}
	
	protected int[] monotonicStack(int[] win, int k) {
		int len = win.length;
		int[] stack = new int[len];
		stack[len - 1] = len - 1;
				
		for(int i = len - 1; i >= k; i--){
			int j = stack[i];
			stack[i - 1] = win[i - 1] < win[j] ? j : i - 1;
		}
		return stack;
	}
	
	protected int[] monotonicStack2(int[] win, int[] stack1, int k) {
		int len = win.length;
		int[] stack2 = new int[len];
		Arrays.fill(stack2, -1);
		
		int last = len - 1 - k;
		stack2[last] = len * last + len - 1;
		
		for(int i = last; i >= k; i--){
			int p = stack2[i] / len;
			int q = stack2[i] % len;
			int s = win[p] + win[q];
			
			int j = stack1[i - 1 + k];
			int t = win[i - 1] + win[j];
			stack2[i - 1] = s <= t ? len * (i - 1) + j : stack2[i];
		}
		return stack2;
	}
	
	protected int[] slidingWindow(int[] nums, int k) {
		int len = nums.length;
		int[] win = new int[len];
		
		int tmp = 0;
		for(int i = 0; i < k; i++){
			tmp += nums[i];
		}
		
		win[k - 1] = tmp;
		for(int i = k; i < len; i++) {
			tmp += nums[i] - nums[i - k];
			win[i] = tmp;
		}
		return win;
	}

}
