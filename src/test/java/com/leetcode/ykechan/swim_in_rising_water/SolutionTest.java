package com.leetcode.ykechan.swim_in_rising_water;

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
		Assertions.assertEquals(3, this.sol.swimInWater(new int[][] {
			{0, 2},
			{1, 3}
		}));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		Assertions.assertEquals(16, this.sol.swimInWater(new int[][] {
			{0, 1, 2, 3, 4},
			{24, 23, 22, 21, 5},
			{12, 13, 14, 15, 16},
			{11, 17, 18, 19, 20},
			{10, 9, 8, 7, 6}
		}));
	}

}
