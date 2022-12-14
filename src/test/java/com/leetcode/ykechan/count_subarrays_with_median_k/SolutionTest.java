package com.leetcode.ykechan.count_subarrays_with_median_k;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

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
		int ans = this.sol.countSubarrays(new int[] {3, 2, 1, 4, 5}, 4);
		Assertions.assertEquals(3, ans);
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		int ans = this.sol.countSubarrays(new int[] {2, 3, 1}, 3);
		Assertions.assertEquals(1, ans);
	}
	
	@Test
	public void shouldBeAbleToPassRandomTestCase1() {
		int[] array = new int[] {5, 7, 11, 10, 1, 3, 15, 12, 9, 6, 13, 0, 2, 14, 4, 8};
		int k = 10;
		
		int oracle = this.sol.bruteForce(array, k);
		int ans = this.sol.countSubarrays(array, k);
		
		Assertions.assertEquals(oracle, ans);
	}
	
	@Test
	public void shouldBeAbleToPassRandomTestCase2() {
		int[] array = new int[] {0, 1, 15, 2, 14, 4, 6, 8, 10, 12, 9, 3, 7, 5, 11, 13};
		int k = 1;
		
		int oracle = this.sol.bruteForce(array, k);
		int ans = this.sol.countSubarrays(array, k);
		
		Assertions.assertEquals(oracle, ans);
	}
	
	@Test
	public void shouldBeAbleToPassRandomTestCase3() {
		int[] array = new int[] {1, 10, 14, 2, 13, 12, 8, 4, 3, 6, 0, 11, 5, 15, 9, 7};
		int k = 8;
		
		int oracle = this.sol.bruteForce(array, k);
		int ans = this.sol.countSubarrays(array, k);
		
		Assertions.assertEquals(oracle, ans);
	}
	
	
	
	@Test
	public void shouldBeAbleToPassRandomTest() {
		int len = 64;
		int limit = 512;
		
		int[] array = IntStream.range(0, len).toArray();
		Random rand = new Random();
		for(int i = 0; i < limit; i++) {
			int k = rand.nextInt(len);
			this.shuffle(array, rand.nextInt(), 4 * len);
			
			int oracle = this.sol.bruteForce(array, k);
			int ans = this.sol.countSubarrays(array, k);
			
			Assertions.assertEquals(oracle, ans, Arrays.toString(array) + " => " + k);
		}
	}
	
	protected void shuffle(int[] array, int init, int limit) {
		Random rand = new Random(init);
		int len = array.length;
		for(int i = 0; i < limit; i++) {
			int a = rand.nextInt(len);
			int b = rand.nextInt(len);
			
			if(a != b){
				int temp = array[a];
				array[a] = array[b];
				array[b] = temp;
			}
		}
	}

}
