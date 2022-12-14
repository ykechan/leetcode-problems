package com.leetcode.ykechan.multiply_strings;

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
		Assertions.assertEquals("6", this.sol.multiply("2", "3"));
	}
	
	@Test
	public void shouldBeAbleToMult2Numbers() {
		Assertions.assertEquals("56088", this.sol.multiply("123", "456"));
	}
	
	@Test
	public void shouldBeAbleToRemoveLeadingZero() {
		Assertions.assertEquals("0", this.sol.multiply("0", "52"));
	}

}
