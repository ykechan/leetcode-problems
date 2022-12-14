package com.leetcode.ykechan.construct_target_array_with_multi_sums;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
		
	public boolean isPossible(int[] target) {
		long sum = Arrays.stream(target).mapToLong(v -> v).sum();
		
		PriorityQueue<Long> heap = new PriorityQueue<>(Comparator.<Long>naturalOrder().reversed());
		for(int t : target) {
			heap.add((long) t);
		}
		
		while(!heap.isEmpty()){
			System.out.println(heap + " => " + sum);
			long t = heap.poll();
			if(t == 1) {
				break;
			}
			
			long y = sum - t;
			if(y == 0) {
				return false;
			}
			if(y == 1){
				return true;
			}
			
			if(t < y) {
				// missed
				return false;
			}
			
			long x = t % y;
			if(x == 0){
				return false;
			}
			heap.add(x);
			sum += x - t;
		}
		return true;
	}	

}
