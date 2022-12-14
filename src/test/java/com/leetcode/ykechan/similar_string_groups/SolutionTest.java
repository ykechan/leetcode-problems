package com.leetcode.ykechan.similar_string_groups;

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
		String[] strs = {"tars", "rats", "arts", "star"};
		Assertions.assertEquals(2, this.sol.numSimilarGroups(strs));
	}
	
	@Test
	public void shouldBeAbleToPassExample2() {
		String[] strs = {"omv", "ovm"};
		Assertions.assertEquals(1, this.sol.numSimilarGroups(strs));
	}

}
