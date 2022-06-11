package com.leetcode.ykechan.scramble_string;

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
		Assertions.assertTrue(this.sol.isScramble("great", "rgeat"));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		Assertions.assertFalse(this.sol.isScramble("abcde", "caebd"));
	}
	
	@Test
	public void shouldBeAbleToPassExample3() {
		Assertions.assertTrue(this.sol.isScramble("a", "a"));
	}

	@Test
	public void shouldBeAbleToPassTestCase1() {
		Assertions.assertTrue(this.sol.isScramble("abb", "bab"));
	}

}
