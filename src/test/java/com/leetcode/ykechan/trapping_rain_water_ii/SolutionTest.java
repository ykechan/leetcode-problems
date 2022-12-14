package com.leetcode.ykechan.trapping_rain_water_ii;

import java.util.Arrays;

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
		int[][] matrix = new int[][] {
			new int[] {1, 4, 3, 1, 3, 2},
			new int[] {3, 2, 1, 3, 2, 4},
			new int[] {2, 3, 3, 2, 3, 1}
		};
		
		Assertions.assertEquals(4, this.sol.trapRainWater(matrix));		
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		int[][] matrix = new int[][] {
			new int[] {3, 3, 3, 3, 3},
			new int[] {3, 2, 2, 2, 3},
			new int[] {3, 2, 1, 2, 3},
			new int[] {3, 2, 2, 2, 3},
			new int[] {3, 3, 3, 3, 3}
		};
		
		Assertions.assertEquals(10, this.sol.trapRainWater(matrix));		
	}
	
	@Test
	public void shouldBeAbleToPassTestCase1() {
		int[][] matrix = new int[][] {
			new int[] {9, 9, 9, 9, 9, 9, 8, 9, 9, 9, 9},
			new int[] {9, 0, 0, 0, 0, 0, 1, 0, 0, 0, 9},
			new int[] {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
			new int[] {9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9},
			new int[] {9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}
		};
		
		System.out.println(this.sol.trapRainWater(matrix));
	}

}
