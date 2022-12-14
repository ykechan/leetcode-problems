package com.leetcode.ykechan.combination_sum_iii;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Solution {
	
	public List<List<Integer>> bruteForce(int k, int n) {
		List<List<Integer>> result = new ArrayList<>();
		
		int limit = 1 << 10;
		for(int i = 0; i < limit; i++){
			List<Integer> items = new ArrayList<>();
			
			int temp = i;
			for(int j = 0; j < 9; j++) {
				if(temp % 2 > 0){
					items.add(j + 1);
				}
				temp /= 2;
			}
			
			if(items.size() == k && items.stream().mapToInt(Integer::intValue).sum() == n){
				result.add(items);
			}
		}
		return result;
	}
	
	public List<List<Integer>> combinationSum3(int k, int n){
		boolean[] done = new boolean[1 << 10];
		Arrays.fill(done, false);
		
		Set<Integer> result = new TreeSet<>();
		
		Deque<Tuple> stack = new ArrayDeque<>();
		stack.push(new Tuple(0, 0));
		while(!stack.isEmpty()){
			Tuple t = stack.pop();
			int num = this.sizeOf(t.elements);
			System.out.println(this.expand(t.elements) + " => " + t.result + ", k=" + num);
			if(num == k && t.result == n){
				result.add(t.elements);
				continue;
			}
			
			if(num >= k || t.result > n){
				continue;
			}
						
			for(int i = 0; i < 9; i++) {
				int mask = 1 << i;
				if((t.elements & mask) > 0){
					continue;
				}
				
				int elem = t.elements | mask;
				if(done[elem]){
					continue;
				}
				
				int sum = t.result + i + 1;
				if(sum <= n){
					stack.push(new Tuple(sum, elem));
				}
				done[elem] = true;
			}
		}
		
		
		return result.stream().map(i -> this.expand(i)).collect(Collectors.toList());
	}
	
	public List<Integer> expand(int elem) {
		List<Integer> result = new ArrayList<>();
		
		int temp = elem;
		for(int i = 0; i < 9; i++) {
			if(temp % 2 > 0) {
				result.add(i + 1);
			}
			temp /= 2;
		}
		return result;
	}
	
	public int sizeOf(int elem) {
		int temp = elem;
		int num = 0;
		for(int i = 0; i < 9; i++) {
			num += temp % 2;
			temp /= 2;
		}
		return num;
	}
	
	public static class Tuple {
		
		public final int result;
		
		public final int elements;

		public Tuple(int result, int elements) {
			this.result = result;
			this.elements = elements;
		}
		
	}

}
