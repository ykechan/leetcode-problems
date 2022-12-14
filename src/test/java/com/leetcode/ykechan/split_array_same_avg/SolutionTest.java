package com.leetcode.ykechan.split_array_same_avg;

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
	public void shouldBeAbleToComputeExample1() {
		System.out.println(this.sol.bruteForce(new int[] {1, 2, 3, 4, 5, 6, 7, 8}));
		System.out.println(this.sol.splitArraySameAverage(new int[] {1, 2, 3, 4, 5, 6, 7, 8}));
	}
	
	@Test
	public void shouldBeAbleToComputeExample2() {
		System.out.println(this.sol.bruteForce(new int[] {3, 1}));
		System.out.println(this.sol.splitArraySameAverage(new int[] {3, 1}));
	}
	
	@Test
	public void shouldBeAbleToPassRandomTest() {
		int limit = 1024;
		int len = 16;
		
		Random rand = new Random();
		
		for(int k = 0; k < limit; k++) {
			int[] array = rand.ints().limit(len).map(Math::abs).map(i -> i % 10001).toArray();
			Assertions.assertEquals( this.sol.bruteForce(array), this.sol.splitArraySameAverage(array), Arrays.toString(array));
			System.out.println("Pass #" + k);
		}
	}

}
