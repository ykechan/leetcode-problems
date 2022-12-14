package com.leetcode.ykechan.shortest_palindrome;

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
		Assertions.assertEquals("aaacecaaa", this.sol.shortestPalindrome("aacecaaa"));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		Assertions.assertEquals("dcbabcd", this.sol.shortestPalindrome("abcd"));
	}

}
