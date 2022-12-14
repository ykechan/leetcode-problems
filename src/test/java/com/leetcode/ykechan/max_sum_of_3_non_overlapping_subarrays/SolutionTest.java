package com.leetcode.ykechan.max_sum_of_3_non_overlapping_subarrays;

import java.util.Arrays;
import java.util.Random;

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
	public void shouldBeAbleToPassExample1() {
		int[] array = {1, 2, 1, 2, 6, 7, 5, 1};
		int[] ans = this.sol.maxSumOfThreeSubarrays(array, 2);
		System.out.println(Arrays.toString(ans));
		System.out.println(Arrays.toString(this.sol.bruteForce(array, 2)));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		int[] array = {1, 2, 1, 2, 1, 2, 1, 2, 1};
		int[] ans = this.sol.maxSumOfThreeSubarrays(array, 2);
		System.out.println(Arrays.toString(ans));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase1() {
		int[] array = {9, 47, 57, 10, 35, 15, 19, 47};
		int[] ans = this.sol.maxSumOfThreeSubarrays(array, 2);
		System.out.println(Arrays.toString(ans));
		System.out.println(Arrays.toString(this.sol.bruteForce(array, 2)));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase2() {
		int[] array = {65, 37, 4, 19, 75, 43, 32, 57};
		int[] ans = this.sol.maxSumOfThreeSubarrays(array, 2);
		System.out.println(Arrays.toString(ans));
		System.out.println(Arrays.toString(this.sol.bruteForce(array, 2)));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase3() {
		int[] array = {90, 28, 4, 55, 4, 89, 31, 41};
		int[] ans = this.sol.maxSumOfThreeSubarrays(array, 2);
		System.out.println(Arrays.toString(ans));
		System.out.println(Arrays.toString(this.sol.bruteForce(array, 2)));
	}
	
	@Test
	public void shouldBeAbleToPassRandomTest() {
		int limit = 64;
		int len = 16;
		int mag = 100;
		
		Random rand = new Random();
		for(int i = 0; i < limit; i++){
			int[] array = rand.ints().limit(len).map(Math::abs).map(n -> 1 + n % mag).toArray();
			int k = 1 + rand.nextInt(len / 3);
			System.out.println("Test " + Arrays.toString(array) + " => " + k);
			int[] ans = this.sol.bruteForce(array, k);
			int[] result = this.sol.maxSumOfThreeSubarrays(array, k);
			Assertions.assertArrayEquals(ans, result, "Expected " + Arrays.toString(ans) + ", result " + Arrays.toString(result));
			System.out.println("Pass #" + i);
		}
	}

}
