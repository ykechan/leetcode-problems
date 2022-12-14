package com.leetcode.ykechan.mindev;

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
		Assertions.assertEquals(1, this.sol.minimumDeviation(new int[] {1, 2, 3, 4}));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		Assertions.assertEquals(3, this.sol.minimumDeviation(new int[] {4, 1, 5, 20, 3}));
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		Assertions.assertEquals(3, this.sol.minimumDeviation(new int[] {2, 10, 8}));
	}
	
	@Test
	public void shouldBeAbleToPassRecurringCase() {
		Assertions.assertEquals(1, this.sol.minimumDeviation(new int[] {3, 5}));
	}
	
	@Test
	public void shouldBeAbleToPassTestCase1() {
		Assertions.assertEquals(315, this.sol.minimumDeviation(new int[] {399,908,648,357,693,502,331,649,596,698}));
	}


}
