package com.leetcode.ykechan.combination_sum_iii;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolutionTest {
	
	private Solution sol;
	
	@BeforeEach
	public void init() {
		this.sol = new Solution();
	}
	
	@Test
	public void shouldBeAbleToComputeExample1() {
		List<List<Integer>> result = this.sol.combinationSum3(3, 7);
		Assertions.assertEquals(1, result.size());
		Assertions.assertArrayEquals(new int[] {1, 2, 4}, result.get(0).stream().mapToInt(Integer::intValue).toArray());
	}
	
	@Test
	public void shouldBeAbleToComputeExample2() {
		List<List<Integer>> result = this.sol.combinationSum3(3, 9);
		Collections.sort(result, this::compare);
		Assertions.assertEquals(3, result.size());
		Assertions.assertArrayEquals(new int[] {1, 2, 6}, result.get(0).stream().mapToInt(Integer::intValue).toArray());
		Assertions.assertArrayEquals(new int[] {1, 3, 5}, result.get(1).stream().mapToInt(Integer::intValue).toArray());
		Assertions.assertArrayEquals(new int[] {2, 3, 4}, result.get(2).stream().mapToInt(Integer::intValue).toArray());
	}
	
	@Test
	public void shouldBeAbleToComputeExample3() {
		List<List<Integer>> result = this.sol.combinationSum3(4, 1);
		Assertions.assertTrue(result.isEmpty());
	}
	
	@Test
	public void shouldBeAbleToComputeK2N10() {
		List<List<Integer>> result = this.sol.combinationSum3(2, 10);
		Collections.sort(result, this::compare);
		Assertions.assertEquals(4, result.size());
		Assertions.assertArrayEquals(new int[] {1, 9}, result.get(0).stream().mapToInt(Integer::intValue).toArray());
		Assertions.assertArrayEquals(new int[] {2, 8}, result.get(1).stream().mapToInt(Integer::intValue).toArray());
		Assertions.assertArrayEquals(new int[] {3, 7}, result.get(2).stream().mapToInt(Integer::intValue).toArray());
		Assertions.assertArrayEquals(new int[] {4, 6}, result.get(3).stream().mapToInt(Integer::intValue).toArray());
	}
	
	@Test
	public void shouldBeAbleToComputeAllInput() {
		for(int k = 2; k < 10; k++) {
			for(int n = 1; n <= 60; n++) {
				List<List<Integer>> result0 = this.sol.bruteForce(k, n);
				List<List<Integer>> result1 = this.sol.combinationSum3(k, n);
				
				Set<List<Integer>> set0 = new TreeSet<>(this::compare);
				Set<List<Integer>> set1 = new TreeSet<>(this::compare);
				
				set0.addAll(result0);
				set1.addAll(result1);
				
				Assertions.assertEquals(set0, set1);
				System.out.println("Pass k = " + k + ", n = " + n);
			}
		}
	}
	
	public <T> boolean equals(Set<T> set0, Set<T> set1) {
		if(set0.size() != set1.size()){
			return true;
		}
		
		for(T t : set0) {
			if(!set1.contains(t)) {
				return false;
			}
		}
		
		for(T t : set1) {
			if(!set0.contains(t)) {
				return false;
			}
		}
		return true;
	}
	
	public int compare(List<Integer> arr0, List<Integer> arr1) {
		int len = Math.min(arr0.size(), arr1.size());
		for(int i = 0; i < len; i++) {
			int a = arr0.get(i);
			int b = arr1.get(i);
			int cmp = a < b ? -1 : a > b ? 1 : 0;
			if(cmp != 0) {
				return cmp;
			}
		}
		return arr0.size() < arr1.size() ? -1 : arr0.size() > arr1.size() ? 1 : 0;
	}

}
