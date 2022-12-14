package com.leetcode.ykechan.partition_array_min_sum_diff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Solution {
	
	public int minimumDifference(int[] nums) {
		int len = nums.length / 2;
		
		int[] prefix = Arrays.copyOfRange(nums, 0, len);
		int[] suffix = Arrays.copyOfRange(nums, len, nums.length);
		
		List<int[]> prefixSums = this.allSums(prefix);
		List<int[]> suffixSums = this.allSums(suffix);
		
		int prefixAll = prefixSums.get(len)[0];
		int suffixAll = suffixSums.get(len)[0];
		int all = prefixAll + suffixAll;
		
		int sum = Arrays.stream(nums).sum();
		if(all != sum) {
			throw new IllegalStateException("Unexpected sum " + all + " and " + sum);
		}
		
		int target = all / 2;
		int max = Math.max(
			prefixAll <= target ? prefixAll : Integer.MIN_VALUE, 
			suffixAll <= target ? suffixAll : Integer.MIN_VALUE);
		
		for(int i = 1; i < len; i++) {
			int[] prefixSum = prefixSums.get(i);
			int[] suffixSum = suffixSums.get(len - i);
			
			for(int p : prefixSum) {
				int qIdx = this.ceilLower(suffixSum, target - p);
				if(qIdx < 0){
					break;
				}
				
				int q = suffixSum[qIdx];
				
				if(p + q <= target){
					max = Math.max(max, p + q);
				}
			}
		}
		
		if(max == Integer.MIN_VALUE){
			throw new IllegalStateException("Not found.");
		}
		return Math.abs(all - 2 * max);
	}
	
	public int ceilLower(int[] array, int target) {
		int lower = 0;
		int upper = array.length - 1;
		
		if(array[lower] > target){
			return -1;
		}
		
		if(array[upper] <= target){
			return upper;
		}
		
		while(upper - lower > 1){
			int mid = (lower + upper) / 2;
			if(array[mid] < target) {
				lower = mid;
			}else{
				upper = mid;
			}
		}
		return array[upper] == target ? upper : lower;
	}
	
	public List<int[]> allSums(int[] nums) {
		List<Set<Integer>> sets = new ArrayList<>();
		sets.add(Collections.emptySet()); // zero length
		for(int i = 1; i <= nums.length; i++){
			sets.add(new TreeSet<>());
		}
		
		for(int i = 0; i < nums.length; i++){
			List<Tuple> tuples = new ArrayList<>();
			tuples.add(new Tuple(1, nums[i]));
			
			for(int j = 0; j < i; j++) {
				Set<Integer> set = sets.get(j + 1);
				for(Integer k : set) {
					tuples.add(new Tuple(j + 2, k + nums[i]));
				}
			}
			
			for(Tuple t : tuples) {
				Set<Integer> set = sets.get(t.weight);
				set.add(t.value);
			}
		}
		
		return sets.stream()
			.map(s -> s.stream().mapToInt(Integer::intValue).toArray())
			.collect(Collectors.toList());
	}
	
	public static class Tuple {
		
		public final int weight, value;

		public Tuple(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
		
	}
	
	public int bruteForce(int[] nums) {
		int len = nums.length;
		int max = 1 << len;
		int min = -1;
		int index = 0;
		for(int k = 1; k < max; k++) {
			int left = 0;
			int right = 0;
			int numL = 0;
			int numR = 0;
			
			int temp = k;			
			for(int i = 0; i < len; i++) {
				switch(temp % 2) {
					case 0 :
						left += nums[i];
						numL++;
						break;
						
					case 1 :	
						right += nums[i];
						numR++;
						break;
				
					default:
						throw new IllegalStateException("Invalid number " + temp);
				}
				
				temp /= 2;
			}
			
			int delta = Math.abs(left - right);
			
			//System.out.println("#" + k + ": " + numL + " <=> " + numR + " -> " + delta);
			if(numL == numR && (min < 0 || min > delta)) {
				min = delta;
				index = k;
			}
		}
		
		int temp = index;
		for(int i = 0; i < len; i++) {
			//System.out.print(temp % 2);
			temp /= 2;
		}
		System.out.println();
		return min;
	}

}
